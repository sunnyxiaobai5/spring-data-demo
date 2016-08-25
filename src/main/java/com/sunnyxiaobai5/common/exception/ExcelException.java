package com.sunnyxiaobai5.common.exception;

import com.sunnyxiaobai5.common.enumeration.ExceptionEnum;

public class ExcelException extends BaseException {

    /**
     * 从异常枚举构造异常
     *
     * @param exceptionEnum 异常信息枚举
     */
    public ExcelException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getKey(), exceptionEnum.getMessage());
    }
}
