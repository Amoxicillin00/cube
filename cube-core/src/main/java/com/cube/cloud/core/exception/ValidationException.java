package com.cube.cloud.core.exception;

import com.cube.cloud.core.constants.StatusCode;

/**
 * Created with IntelliJ IDEA.
 * 验证异常
 * @author Long
 * @date 2023-04-14 10:21
 */
public class ValidationException extends SystemException{

    private static final long serialVersionUID = 7599489978233456935L;



    /**
     * 无构造实例化
     */
    public ValidationException() {
        super();
        this.setCode(StatusCode.SYSTEM_ERROR_VALIDATION);
    }

    /**
     * 实例化
     * @param message 消息
     */
    public ValidationException(String message) {
        super(message);
        this.setCode(StatusCode.SYSTEM_ERROR_VALIDATION);
    }

    /**
     * 实例化
     * @param message 消息
     * @param cause   源异常
     */
    public ValidationException(String message, Throwable cause) {
        super(message, cause);
        this.setCode(StatusCode.SYSTEM_ERROR_VALIDATION);
    }

    /**
     * 实例化
     * @param cause 源异常
     */
    public ValidationException(Throwable cause) {
        super(cause);
        this.setCode(StatusCode.SYSTEM_ERROR_VALIDATION);
    }

    /**
     * 实例化
     * @param message            消息
     * @param cause              源异常
     * @param enableSuppression
     * @param writableStackTrace 写入跟踪
     */
    protected ValidationException(String message, Throwable cause, boolean enableSuppression,
                                  boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.setCode(StatusCode.SYSTEM_ERROR_VALIDATION);
    }
}
