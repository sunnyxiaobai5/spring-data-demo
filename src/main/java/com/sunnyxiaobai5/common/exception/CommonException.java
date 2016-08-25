package com.sunnyxiaobai5.common.exception;

public class CommonException extends BaseException {

    public CommonException() {
        super(ExceptionCode.COMMON_EXCEPTION);
    }

    /**
     * @param message 异常说明
     */
    public CommonException(String message) {
        super(ExceptionCode.COMMON_EXCEPTION, message);
    }
}
