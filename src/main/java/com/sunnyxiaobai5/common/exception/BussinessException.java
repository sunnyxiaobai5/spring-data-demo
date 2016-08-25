package com.sunnyxiaobai5.common.exception;

public class BussinessException extends BaseException {

    public BussinessException() {
        super(ExceptionCode.BUSSINESS_EXCEPTION);
    }

    /**
     * @param message 异常说明
     */
    public BussinessException(String message) {
        super(ExceptionCode.BUSSINESS_EXCEPTION, message);
    }
}
