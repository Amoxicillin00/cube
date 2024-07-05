package com.cube.cloud.core.exception;

import com.cube.cloud.core.constants.StatusCode;

/**
 * Created with IntelliJ IDEA.
 * 格式异常
 * @author Long
 * @date 2023-04-14 11:47
 */
public class FormatException extends SystemException{

    private static final long serialVersionUID = 7480219918490800356L;


    /**
     * 无构造实例化
     */
    public FormatException() {
        super("格式错误");
        this.setCode(StatusCode.SYSTEM_ERROR_FORMAT);
    }

    /**
     * 实例化
     * @param message 消息
     */
    public FormatException(String message) {
        super(message);
        this.setCode(StatusCode.SYSTEM_ERROR_FORMAT);
    }

    /**
     * 实例化
     * @param message 消息
     * @param cause   源异常
     */
    public FormatException(String message, Throwable cause) {
        super(message, cause);
        this.setCode(StatusCode.SYSTEM_ERROR_FORMAT);
    }

    /**
     * 实例化
     * @param cause 源异常
     */
    public FormatException(Throwable cause) {
        super(cause);
        this.setCode(StatusCode.SYSTEM_ERROR_FORMAT);
    }

    /**
     * 实例化
     * @param message            消息
     * @param cause              源异常
     * @param enableSuppression
     * @param writableStackTrace 写入跟踪
     */
    protected FormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.setCode(StatusCode.SYSTEM_ERROR_FORMAT);
    }
}
