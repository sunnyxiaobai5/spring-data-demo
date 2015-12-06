/*******************************************************************************
 * sunnyxiaobai5@gmail.com
 * <p>
 * <li>项目名称: spring-data-demo</li>
 * <li>完整包名: com.sunnyxiaobai5.config</li>
 * <li>文件名称: ExportPdfProperties.java</li>
 * <li>内容摘要: </li>
 * <li>内容描述: </li>
 * <li>其他说明: </li>
 * <li>@author Xiangyong Zeng</li>
 ******************************************************************************/
package com.sunnyxiaobai5.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(locations = "classpath:pdf.properties", prefix = "export")
public class ExportPdfProperties {

    //字体文件地址
    private String fontPath;

    //导出pdf表格头部字体大小
    private float fontHeaderSize;

    //导出pdf表格数据部分字体大小
    private float fontBodySize;

    //导出pdf表格表注（底部行）字体大小
    private float fontFooterSize;

    public String getFontPath() {
        return fontPath;
    }

    public void setFontPath(String fontPath) {
        this.fontPath = fontPath;
    }

    public float getFontHeaderSize() {
        return fontHeaderSize;
    }

    public void setFontHeaderSize(float fontHeaderSize) {
        this.fontHeaderSize = fontHeaderSize;
    }

    public float getFontBodySize() {
        return fontBodySize;
    }

    public void setFontBodySize(float fontBodySize) {
        this.fontBodySize = fontBodySize;
    }

    public float getFontFooterSize() {
        return fontFooterSize;
    }

    public void setFontFooterSize(float fontFooterSize) {
        this.fontFooterSize = fontFooterSize;
    }
}
