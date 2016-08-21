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

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class AttachmentDTO {
    private String id;
    private String name;
    private String type;
    private Date lastModifiedDate;
    private Long size;
    private Integer chunks;
    private Integer chunk;
    private MultipartFile file;

    private String downloadPath;
    private String filePath;

    private MultipartInfo multipartInfo;

    public void buildMultipartInfo() {
        if (null != getChunks()) {
            this.multipartInfo = new MultipartInfo(getFilename(), getChunks(), getChunk());
        }
    }

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

    public String getDownloadPath() {
        return downloadPath;
    }

    public void setDownloadPath(String downloadPath) {
        this.downloadPath = downloadPath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public MultipartInfo getMultipartInfo() {
        return multipartInfo;
    }

    public void setMultipartInfo(MultipartInfo multipartInfo) {
        this.multipartInfo = multipartInfo;
    }

    @JsonIgnore
    public String getExt() {
        int dotIndex = getFile().getOriginalFilename().lastIndexOf(".");
        if (dotIndex <= 0) {
            return "";
        }
        return getFile().getOriginalFilename().substring(dotIndex);
    }

    @JsonIgnore
    public String getFilename() {
        return getFile().getOriginalFilename();
    }

    public class MultipartInfo {
        private String key;
        private String tempDir;
        private String filename;
        private Integer chunks = 0;
        private Integer chunk;
        private Set<Integer> chunkNos = new HashSet<>();

        MultipartInfo(String key, Integer chunks, Integer chunk) {
            this.key = key;
            this.chunks = chunks;
            this.chunk = chunk;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getTempDir() {
            return tempDir;
        }

        public void setTempDir(String tempDir) {
            this.tempDir = tempDir;
        }

        public String getFilename() {
            return filename;
        }

        public void setFilename(String filename) {
            this.filename = filename;
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

        public void addChunk(Integer chunkNo) {
            this.chunkNos.add(chunkNo);
        }

        public boolean isComplete() {
            return this.getChunks() == this.chunkNos.size();
        }
    }
}
