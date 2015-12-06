/*******************************************************************************
 * sunnyxiaobai5@gmail.com
 * <p>
 * <li>项目名称: spring-data-demo</li>
 * <li>完整包名: com.sunnyxiaobai5.common.enumeration</li>
 * <li>文件名称: ExceptionEnum.java</li>
 * <li>内容摘要: </li>
 * <li>内容描述: </li>
 * <li>其他说明: </li>
 * <li>@author Xiangyong Zeng</li>
 ******************************************************************************/
package com.sunnyxiaobai5.common.enumeration;

public enum ExceptionEnum {
    EXPORT_NO_ANNOTATION(1, "没有任何列添加导出注解"),
    EXPORT_NO_COLUMN(10001, "没有要导出的列");

    private Integer key;

    private String message;

    ExceptionEnum(Integer key, String message) {
        this.key = key;
        this.message = message;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
