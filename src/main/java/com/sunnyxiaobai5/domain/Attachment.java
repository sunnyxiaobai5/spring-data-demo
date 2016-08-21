/*******************************************************************************
 * sunnyxiaobai5@gmail.com
 * <p>
 * <li>项目名称: spring-data-demo</li>
 * <li>完整包名: com.sunnyxiaobai5.domain</li>
 * <li>文件名称: Attachment.java</li>
 * <li>内容摘要: </li>
 * <li>内容描述: </li>
 * <li>其他说明: </li>
 * <li>@author Xiangyong Zeng</li>
 ******************************************************************************/
package com.sunnyxiaobai5.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sunnyxiaobai5.common.BaseStringEntity;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ATTACHMENT")
@Where(clause = "IS_DELETE=0")
public class Attachment extends BaseStringEntity<String> {
    /**
     * 文件扩展名
     */
    private String ext;

    /**
     * 文件名
     */
    private String filename;

    /**
     * 文件路径（包括文件名）
     */
    @Column(name = "FILE_PATH")
    private String filePath;

    /**
     * 下载路径
     */
    @Column(name = "DOWNLOAD_PATH")
    private String downloadPath;

    /**
     * 文件大小（单位：byte）
     */
    @NotNull
    @Column(name = "FILE_SIZE", nullable = false)
    private Long fileSize = 0L;

    /**
     * 是否逻辑删除
     */
    @NotNull
    @JsonIgnore
    @Column(name = "IS_DELETE", nullable = false)
    private Boolean isDelete = false;

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getDownloadPath() {
        return downloadPath;
    }

    public void setDownloadPath(String downloadPath) {
        this.downloadPath = downloadPath;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }
}
