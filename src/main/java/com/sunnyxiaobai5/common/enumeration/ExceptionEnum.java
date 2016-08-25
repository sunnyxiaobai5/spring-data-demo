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

import com.sunnyxiaobai5.common.exception.ExceptionCode;

public enum ExceptionEnum {
    EXPORT_NO_ANNOTATION(ExceptionCode.EXCEL_INNER_EXCEPTION, "没有任何列添加导出注解"),
    EXPORT_NO_COLUMN(ExceptionCode.EXCEL_OUTER_EXCEPTION, "没有要导出的列");

    private Integer key;

    private String message;

    ExceptionEnum(Integer key, String message) {
        this.key = key;
        this.message = message;
    }

    public Integer getKey() {
        return key;
    }

    public String getMessage() {
        return message;
    }
}
