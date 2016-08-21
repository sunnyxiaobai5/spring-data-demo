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

    public AttachmentInfo buildAttachmentInfo() {
        AttachmentInfo attachmentInfo = new AttachmentInfo();
        attachmentInfo.setExt(getExt());
        attachmentInfo.setFilename(getFilename());
        attachmentInfo.setFileSize(size);
        attachmentInfo.setChunked(null != chunks);
        return attachmentInfo;
    }
}
