package com.sunnyxiaobai5.common.exception;

public final class ExceptionCode {
    private ExceptionCode() {
    }

    /**
     * 系统内部异常
     */
    public static final Integer SYSTEM_EXCEPTION = 0;

    /**
     * 自定义异常（全局处理）
     */
    public static final Integer COMMON_EXCEPTION = 1;

    /**
     * 自定义异常（非全局处理）
     */
    public static final Integer CUSTOM_EXCEPTION = 2;
}

