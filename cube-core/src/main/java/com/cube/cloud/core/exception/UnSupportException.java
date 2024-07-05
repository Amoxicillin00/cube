package com.cube.cloud.core.exception;

import com.cube.cloud.core.constants.StatusCode;

/**
 * Created with IntelliJ IDEA.
 * 不支持异常
 * @author Long
 * @date 2023-04-14 11:38
 */
public class UnSupportException extends SystemException{

    private static final long serialVersionUID = -2524021065964762206L;


    /**
     * 无构造实例化
     */
    public UnSupportException() {
        super("不支持的操作");
    }

    /**
     * 实例化
     * @param message 消息
     */
    public UnSupportException(String message) {
        super(message);
    }

    /**
     * 实例化
     * @param message 消息
     * @param cause   源异常
     */
    public UnSupportException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 实例化
     * @param cause 源异常
     */
    public UnSupportException(Throwable cause) {
        super(cause);
    }

    /**
     * 实例化
     * @param message            消息
     * @param cause              源异常
     * @param enableSuppression
     * @param writableStackTrace 写入跟踪
     */
    protected UnSupportException(String message, Throwable cause, boolean enableSuppression,
                                  boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


    @Override
    public final int getCode() {
        return StatusCode.SYSTEM_ERROR_UN_SUPPORT;
    }

    @Override
    public final void setCode(int code) {

    }
}
