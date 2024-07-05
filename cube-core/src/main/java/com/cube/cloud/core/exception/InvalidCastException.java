package com.cube.cloud.core.exception;

import com.cube.cloud.core.constants.StatusCode;

/**
 * Created with IntelliJ IDEA.
 * 转换异常
 * @author Long
 * @date 2023-04-14 11:42
 */
public class InvalidCastException extends SystemException{

    private static final long serialVersionUID = 8536117080948909764L;


    /**
     * 无构造实例化
     */
    public InvalidCastException() {
        super();
        this.setCode(StatusCode.SYSTEM_ERROR_INVALID_CAST);
    }

    /**
     * 实例化
     * @param message 消息
     */
    public InvalidCastException(String message) {
        super(message);
        this.setCode(StatusCode.SYSTEM_ERROR_INVALID_CAST);
    }

    /**
     * 实例化
     * @param message 消息
     * @param cause   源异常
     */
    public InvalidCastException(String message, Throwable cause) {
        super(message, cause);
        this.setCode(StatusCode.SYSTEM_ERROR_INVALID_CAST);
    }

    /**
     * 实例化
     * @param cause 源异常
     */
    public InvalidCastException(Throwable cause) {
        super(cause);
        this.setCode(StatusCode.SYSTEM_ERROR_INVALID_CAST);
    }

    /**
     * 实例化
     * @param message            消息
     * @param cause              源异常
     * @param enableSuppression
     * @param writableStackTrace 写入跟踪
     */
    protected InvalidCastException(String message, Throwable cause, boolean enableSuppression,
                                   boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
