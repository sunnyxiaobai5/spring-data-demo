package com.sunnyxiaobai5.web.rest.resource;

import com.sunnyxiaobai5.domain.Attachment;
import com.sunnyxiaobai5.service.AttachmentService;
import com.sunnyxiaobai5.util.SecurityUtils;
import com.sunnyxiaobai5.web.rest.dto.AttachmentDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/attachment")
public class AttachmentResource {

    @Resource
    private AttachmentService attachmentService;

    private static final String UPLOAD_DIR = "upload";

    private static final String UPLOAD_DIR_TMP = "upload_tmp";

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
        if (null != attachmentDTO.getChunks()) {
            //分片上传，先将分片缓存到redis
            boolean complete = cache(attachmentDTO);
            //分片未上传完成，返回
            if (!complete) {
                return;
            }

            //分片上传完成，合并
            File file = merge(attachmentDTO);
            //合并未成功，返回
            if (null == file) {
                return;
            }
            //合并成功，上传文件服务器
            boolean success = uploadToFs(file);

            //上传成功，清空redis缓存
            if (success) {
                clearCache(attachmentDTO);
            }
        } else {
            //非分片上传，直接上传到文件服务器
            uploadToFs(attachmentDTO.getFile());
        }
        //保存文件信息到数据库
        saveInfo(attachmentDTO);
    }

    @RequestMapping(value = "/uploadToServer", method = RequestMethod.POST)
    public synchronized AttachmentDTO uploadToServer(HttpServletRequest request, AttachmentDTO attachmentDTO) throws IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd" + File.separator + "hh-mm-ss");

        if (null != attachmentDTO.getChunks()) {
            attachmentDTO.buildMultipartInfo();
            String key = getKey(attachmentDTO.getMultipartInfo().getKey());
            AttachmentDTO.MultipartInfo multipartInfo = (AttachmentDTO.MultipartInfo) request.getSession().getAttribute(key);

            //如果value有值，则表明不是第一次，使用旧的，否者新生成路径
            String tempDir;
            if (null != multipartInfo) {
                tempDir = multipartInfo.getTempDir();
            } else {
                tempDir = dateFormat.format(new Date());
                multipartInfo = attachmentDTO.getMultipartInfo();
                multipartInfo.setTempDir(tempDir);
            }
            multipartInfo.addChunk(attachmentDTO.getChunk());
            attachmentDTO.setMultipartInfo(multipartInfo);
            request.getSession().setAttribute(key, multipartInfo);

            String tempDirPath = AttachmentResource.UPLOAD_DIR + File.separator + tempDir;
            String realDirPath = request.getSession().getServletContext().getRealPath("/" + tempDirPath);
            String downloadPath = File.separator + tempDirPath + File.separator + attachmentDTO.getFilename();

            String filename = UUID.randomUUID().toString() + attachmentDTO.getExt();
            String filePath = realDirPath + File.separator + filename;
            attachmentDTO.setFilePath(filePath);

            //创建目录
            File directory = new File(realDirPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            //保存临时文件
            File dest = new File(filePath);
            attachmentDTO.getFile().transferTo(dest);

            //合并
            File file = mergeToServer(attachmentDTO);

            //若合并完成，保存最终文件并保存文件相关信息
            if (null != file) {
                //保存文件
                saveToServer(file);
                //保存文件信息
                attachmentDTO.setDownloadPath(downloadPath);
                saveInfo(attachmentDTO);
                request.getSession().removeAttribute(key);
            }
        } else {
            String tempDirPath = AttachmentResource.UPLOAD_DIR + File.separator + dateFormat.format(new Date());
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
            attachmentDTO.setDownloadPath(downloadPath);
            saveInfo(attachmentDTO);
        }
        attachmentDTO.setFile(null);
        return attachmentDTO;
    }

    /**
     * 分片上传合并（从本地文件系统合并）
     *
     * @param attachmentDTO
     * @return 合并后的文件
     */
    private File mergeToServer(AttachmentDTO attachmentDTO) {
        if (attachmentDTO.getMultipartInfo().isComplete()) {
            return new File(attachmentDTO.getFilePath());
        }
        return null;
    }

    @RequestMapping(value = "uploadToServer")
    public void uploadToServer(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        //得到上传的文件map集合对象
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();

        //获取分片序号
        String chunkStr = request.getParameter("chunk");
        Integer chunk = null;
        if (StringUtils.isNotBlank(chunkStr)) {
            chunk = Integer.parseInt(chunkStr);
        }

        //循环遍历存放上传对象
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile multipartFile = entity.getValue();

            String uuid = UUID.randomUUID().toString();
            Attachment attachment = new Attachment();
            attachment.setFilename(multipartFile.getOriginalFilename());
            attachment.setFilePath(multipartFile.getOriginalFilename());
//            chunkStreamVO.setKey(uuid);
//            chunkStreamVO.setValue(multipartFile.getBytes());
            attachmentService.save(attachment);
        }
    }

    /**
     * 删除文件
     *
     * @param id 文件信息ID
     */
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public void delete(String id) {
        attachmentService.delete(id);
    }

    /**
     * 清空redis缓存
     *
     * @param attachmentDTO
     */
    private void clearCache(AttachmentDTO attachmentDTO) {
    }

    /**
     * 保存文件信息到数据库
     *
     * @param attachmentDTO
     */
    private void saveInfo(AttachmentDTO attachmentDTO) {
        Attachment attachment = new Attachment();
        attachment.setExt(attachmentDTO.getExt());
        attachment.setFilename(attachmentDTO.getFilename());
        attachment.setFilePath(attachmentDTO.getDownloadPath());
        attachment.setFileSize(attachmentDTO.getSize());
        attachmentService.save(attachment);
    }

    /**
     * 上传到文件服务器
     *
     * @param multipartFile
     * @throws IOException
     */
    private boolean uploadToFs(MultipartFile multipartFile) throws IOException {
        return false;
    }

    /**
     * 上传到文件服务器
     *
     * @param file
     * @throws IOException
     */
    private boolean uploadToFs(File file) throws IOException {
        return false;
    }

    /**
     * 分片上传时，先缓存到redis中
     *
     * @param attachmentDTO
     */
    private boolean cache(AttachmentDTO attachmentDTO) {
        return false;
    }

    /**
     * 分片上传合并（从redis中合并）
     *
     * @param attachmentDTO
     * @return 合并后的文件
     */
    private File merge(AttachmentDTO attachmentDTO) {
        return null;
    }

    /**
     * 保存文件（本地文件系统）
     *
     * @param file 要保存的文件
     */
    private void saveToServer(File file) {

    }

    /**
     * 获取分片文件的key（以文件名作为key的一部分）
     *
     * @param filename 文件名
     * @return
     */
    private String getKey(String filename) {
        return SecurityUtils.getCurrentLogin() + "_FILE_UPLOAD_" + filename;
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<String> handleException(MaxUploadSizeExceededException ex) {
        System.out.println("=====================" + ex.getClass().getName());
        return ResponseEntity.ok("ok");
    }
}
