package com.sunnyxiaobai5.web.rest.resource;

import com.sunnyxiaobai5.common.enumeration.RedisKeyPrefixEnum;
import com.sunnyxiaobai5.config.DirectoryProperties;
import com.sunnyxiaobai5.domain.Attachment;
import com.sunnyxiaobai5.service.AttachmentService;
import com.sunnyxiaobai5.util.RedisUtils;
import com.sunnyxiaobai5.util.SecurityUtils;
import com.sunnyxiaobai5.web.rest.dto.AttachmentDTO;
import com.sunnyxiaobai5.web.rest.dto.AttachmentInfoDTO;
import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/attachment")
public class AttachmentResource {

    @Resource
    private AttachmentService attachmentService;

    @Resource
    private DirectoryProperties directoryProperties;

    /**
     * 文件上传
     * <p>
     * 未分片：直接上传
     * 分片：
     * 1：缓存到redis
     * 2：合并redis中分片
     * 3：上传合并结果到文件服务器
     * 4：清空redis缓存
     *
     * @param attachmentDTO 文件信息（前端）
     * @throws IOException
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public void upload(AttachmentDTO attachmentDTO) throws IOException {
        AttachmentInfoDTO info = attachmentDTO.buildAttachmentInfo();
        if (null != attachmentDTO.getChunks()) {
            String key = RedisUtils.getRedisKey(RedisKeyPrefixEnum.FILE_UPLOAD.getKey(), attachmentDTO.getFilename());
            RedisUtils.listLeftPush(key, attachmentDTO.buildChunk());
        } else {
            //非分片上传，直接上传到文件服务器
            String fastDfsID = uploadToFs(attachmentDTO.getFile());
            //TODO 拼接下载路径
            info.setDownloadPath(fastDfsID);
            //保存文件信息到数据库
            saveInfo(info);
        }
    }

    @RequestMapping(value = "/uploadToServer", method = RequestMethod.POST)
    public synchronized AttachmentInfoDTO uploadToServer(HttpServletRequest request, AttachmentDTO attachmentDTO) throws IOException, NoSuchAlgorithmException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd" + File.separator + "hh-mm-ss");

        AttachmentInfoDTO info = attachmentDTO.buildAttachmentInfo();

        if (null != attachmentDTO.getChunks()) {
            String key = getSessionKey(RedisKeyPrefixEnum.FILE_UPLOAD.getKey(), attachmentDTO.getFilename());
            String value = (String) request.getSession().getAttribute(key);

            //如果value有值，则表明不是第一次，使用旧的，否者新生成路径
            String tempDir;
            if (null != value) {
                tempDir = value;
            } else {
                tempDir = dateFormat.format(new Date());
                request.getSession().setAttribute(key, tempDir);
            }

            String tempDirPath = directoryProperties.getFileupload() + File.separator + tempDir;
            String realDirPath = request.getSession().getServletContext().getRealPath("/" + tempDirPath);
            String downloadPath = File.separator + tempDirPath + File.separator + attachmentDTO.getFilename();

            String partName = UUID.randomUUID().toString() + "." + attachmentDTO.getChunk() + attachmentDTO.getExt();
            String partPath = realDirPath + File.separator + partName;
            String filePath = realDirPath + File.separator + attachmentDTO.getFilename();
            info.setFilePath(filePath);
            info.setDownloadPath(downloadPath);

            //创建目录
            File directory = new File(realDirPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            //保存临时文件
            File dest = new File(partPath);
            attachmentDTO.getFile().transferTo(dest);

        } else {
            String tempDirPath = directoryProperties.getFileupload() + File.separator + dateFormat.format(new Date());
            String realDirPath = request.getSession().getServletContext().getRealPath("/" + tempDirPath);
            String downloadPath = File.separator + tempDirPath + File.separator + attachmentDTO.getFilename();

            String filename = attachmentDTO.getFilename();
            String filePath = realDirPath + File.separator + filename;

            //创建目录
            File tempDir = new File(realDirPath);
            if (!tempDir.exists()) {
                tempDir.mkdirs();
            }

            //保存文件
            File dest = new File(filePath);
            attachmentDTO.getFile().transferTo(dest);

            //保存文件信息
            info.setFilePath(dest.getCanonicalPath());
            info.setDownloadPath(downloadPath);
            saveInfo(info);
        }
        return info;
    }

    /**
     * 分片上传合并（从本地文件系统合并）
     *
     * @param info 文件信息
     */
    @RequestMapping(value = "/mergeToServer", method = RequestMethod.POST)
    public void mergeToServer(HttpServletRequest request, @RequestBody AttachmentInfoDTO info) throws IOException {
        //保存文件
        saveToServer(info);
        //保存文件信息
        saveInfo(info);
        String sessionKey = getSessionKey(RedisKeyPrefixEnum.FILE_UPLOAD.getKey(), info.getFilename());
        request.getSession().removeAttribute(sessionKey);
    }

    /**
     * 上传到文件服务器
     *
     * @param info 文件信息
     * @throws IOException
     */
    @RequestMapping(value = "/uploadToFs", method = RequestMethod.POST)
    private String uploadToFs(@RequestBody AttachmentInfoDTO info) throws IOException {
        File file = merge(info);
        //TODO 拼接下载路径
        String fastDfsID = uploadToFs(file);
        info.setDownloadPath(fastDfsID);
        //保存文件信息到数据库
        saveInfo(info);
        //清Redis中数据
        RedisUtils.delete(RedisUtils.getRedisKey(RedisKeyPrefixEnum.FILE_UPLOAD.getKey(), info.getFilename()));
        return fastDfsID;
    }

    /**
     * 分片上传合并（从redis中合并）
     *
     * @param info
     * @return 合并后的文件
     */
    private File merge(@RequestBody AttachmentInfoDTO info) throws IOException {
        String key = RedisUtils.getRedisKey(RedisKeyPrefixEnum.FILE_UPLOAD.getKey(), info.getFilename());
        List<AttachmentDTO.ChunkDTO> chunkDTOs = RedisUtils.listPopAll(key);
        chunkDTOs.sort((o1, o2) -> o1.getChunk() - o2.getChunk());
        File result = new File(info.getFilePath());
        FileOutputStream fos = new FileOutputStream(result);
        chunkDTOs.stream().forEach(chunkDTO -> {
            try {
                fos.write(chunkDTO.getBytes());
            } catch (IOException e) {
                //TODO 异常处理
                e.printStackTrace();
            }
        });
        fos.close();
        return result;
    }

    /**
     * 上传到文件服务器
     *
     * @param file
     * @throws IOException
     */
    private String uploadToFs(File file) throws IOException {
        return null;
    }

    /**
     * 上传到文件服务器
     *
     * @param file
     * @throws IOException
     */
    private String uploadToFs(MultipartFile file) throws IOException {
        return null;
    }


    /**
     * 保存文件信息到数据库
     *
     * @param info 文件相关信息
     */
    private void saveInfo(AttachmentInfoDTO info) {
        Attachment attachment = new Attachment();
        attachment.setExt(info.getExt());
        attachment.setFilename(info.getFilename());
        attachment.setFilePath(info.getFilePath());
        attachment.setDownloadPath(info.getDownloadPath());
        attachment.setFileSize(info.getFileSize());
        attachmentService.save(attachment);
    }

    /**
     * 删除文件
     *
     * @param id 文件信息ID
     */
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void delete(String id) {
        attachmentService.delete(id);
    }

    /**
     * 保存文件（本地文件系统）
     *
     * @param info 要保存的文件
     */
    private void saveToServer(AttachmentInfoDTO info) throws IOException {
        File file = new File(info.getFilePath());
        File dir = new File(file.getParent());
        File[] parts = dir.listFiles();

        //TODO 异常
        if (null == parts || parts.length == 0) {
            throw new RuntimeException("分片文件不存在");
        }

        parts = Arrays.asList(parts).stream().sorted((file1, file2) -> {
            try {
                String filename1 = file1.getCanonicalPath();
                String filename2 = file2.getCanonicalPath();
                Integer file1Chunk = Integer.parseInt(filename1.substring(0, filename1.lastIndexOf(".")).substring(filename1.substring(0, filename1.lastIndexOf(".")).lastIndexOf(".") + 1));
                Integer file2Chunk = Integer.parseInt(filename2.substring(0, filename2.lastIndexOf(".")).substring(filename2.substring(0, filename2.lastIndexOf(".")).lastIndexOf(".") + 1));
                return file1Chunk - file2Chunk;
            } catch (IOException e) {
                //TODO 异常
                e.printStackTrace();
            }
            return 0;
        }).collect(Collectors.toList()).toArray(new File[0]);


        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
        for (File part : parts) {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(part));
            int n;
            byte[] buff = new byte[1024];
            while ((n = in.read(buff)) != -1) {
                out.write(buff, 0, n);
            }
            in.close();
            part.delete();
        }
        out.close();
    }

    /**
     * 获取分片文件的key（以文件名作为key的一部分）
     *
     * @param identity 标识
     * @return
     */
    private String getSessionKey(String prefix, String identity) {
        return SecurityUtils.getCurrentLogin() + "_" + prefix + "_" + identity;
    }
}
