/*******************************************************************************
 * sunnyxiaobai5@gmail.com
 * <p>
 * <li>项目名称: spring-data-demo</li>
 * <li>完整包名: com.sunnyxiaobai5.common.exception</li>
 * <li>文件名称: BaseException.java</li>
 * <li>内容摘要: </li>
 * <li>内容描述: </li>
 * <li>其他说明: </li>
 * <li>@author Xiangyong Zeng</li>
 ******************************************************************************/
package com.sunnyxiaobai5.common.exception;

public class BaseException extends Exception {

    private final Integer code;

    /**
     * @param code 异常状态码
     */
    public BaseException(Integer code) {
        this.code = code;
    }

    /**
     * @param code  异常状态码
     * @param message 异常说明
     */
    public BaseException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
