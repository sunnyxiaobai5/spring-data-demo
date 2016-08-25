package com.sunnyxiaobai5.common.exception;

public class BussinessException extends BaseException {

    public BussinessException() {
        super(ExceptionCode.CUSTOM_EXCEPTION);
    }

    /**
     * @param message 异常说明
     */
    public BussinessException(String message) {
        super(ExceptionCode.CUSTOM_EXCEPTION, message);
    }
}
