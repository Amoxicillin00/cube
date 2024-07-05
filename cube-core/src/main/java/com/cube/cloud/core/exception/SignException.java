package com.cube.cloud.core.exception;

import com.cube.cloud.core.constants.StatusCode;

/**
 * Created with IntelliJ IDEA.
 * 签名异常
 * @author Long
 * @date 2023-04-14 11:49
 */
public class SignException extends SystemException{

    private static final long serialVersionUID = 3636097735164449850L;


    /**
     * 无构造实例化
     */
    public SignException() {
        super();
        this.setCode(StatusCode.SYSTEM_ERROR_SIGN);
    }

    /**
     * 实例化
     * @param message 消息
     */
    public SignException(String message) {
        super(message);
        this.setCode(StatusCode.SYSTEM_ERROR_SIGN);
    }

    /**
     * 实例化
     * @param message 消息
     * @param cause   源异常
     */
    public SignException(String message, Throwable cause) {
        super(message, cause);
        this.setCode(StatusCode.SYSTEM_ERROR_SIGN);
    }

    /**
     * 实例化
     * @param cause 源异常
     */
    public SignException(Throwable cause) {
        super(cause);
        this.setCode(StatusCode.SYSTEM_ERROR_SIGN);
    }

    /**
     * 实例化
     * @param message            消息
     * @param cause              源异常
     * @param enableSuppression
     * @param writableStackTrace 写入跟踪
     */
    protected SignException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.setCode(StatusCode.SYSTEM_ERROR_SIGN);
    }
}
