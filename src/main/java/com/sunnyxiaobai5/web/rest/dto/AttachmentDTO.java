/*******************************************************************************
 * sunnyxiaobai5@gmail.com
 * <p>
 * <li>项目名称: spring-data-demo</li>
 * <li>完整包名: com.sunnyxiaobai5.domain</li>
 * <li>文件名称: AttachmentDTO.java</li>
 * <li>内容摘要: </li>
 * <li>内容描述: </li>
 * <li>其他说明: </li>
 * <li>@author Xiangyong Zeng</li>
 ******************************************************************************/
package com.sunnyxiaobai5.web.rest.dto;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

public class AttachmentDTO {
    private String id;
    private String name;
    private String type;
    private Date lastModifiedDate;
    private Long size;
    private Integer chunks;
    private Integer chunk;
    private MultipartFile file;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Integer getChunks() {
        return chunks;
    }

    public void setChunks(Integer chunks) {
        this.chunks = chunks;
    }

    public Integer getChunk() {
        return chunk;
    }

    public void setChunk(Integer chunk) {
        this.chunk = chunk;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getExt() {
        int dotIndex = getFile().getOriginalFilename().lastIndexOf(".");
        if (dotIndex <= 0) {
            return "";
        }
        return getFile().getOriginalFilename().substring(dotIndex);
    }

    public String getFilename() {
        return getFile().getOriginalFilename();
    }

    public AttachmentInfoDTO buildAttachmentInfo() {
        AttachmentInfoDTO infoDTO = new AttachmentInfoDTO();
        infoDTO.setExt(getExt());
        infoDTO.setFilename(getFilename());
        infoDTO.setFileSize(size);
        infoDTO.setChunked(null != chunks);
        return infoDTO;
    }

    public class ChunkDTO {
        private byte[] bytes;
        private Integer chunk;

        ChunkDTO() {
            try {
                this.bytes = getFile().getBytes();
            } catch (IOException e) {
                //TODO 异常处理
                e.printStackTrace();
            }
            this.chunk = getChunk();
        }

        public byte[] getBytes() {
            return bytes;
        }

        public void setBytes(byte[] bytes) {
            this.bytes = bytes;
        }

        public Integer getChunk() {
            return chunk;
        }

        public void setChunk(Integer chunk) {
            this.chunk = chunk;
        }
    }


    public ChunkDTO buildChunk() {
        return new ChunkDTO();
    }
}
