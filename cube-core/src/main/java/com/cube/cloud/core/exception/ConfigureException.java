package com.cube.cloud.core.exception;

import com.cube.cloud.core.constants.StatusCode;

/**
 * Created with IntelliJ IDEA.
 * 配置异常
 * @author Long
 * @date 2023-04-14 11:56
 */
public class ConfigureException extends SystemException{

    private static final long serialVersionUID = 1273327575910489802L;


    /**
     * 无构造实例化
     */
    public ConfigureException() {
        super();
        this.setCode(StatusCode.SYSTEM_ERROR_CONFIGURE);
    }

    /**
     * 实例化
     * @param message 消息
     */
    public ConfigureException(String message) {
        super(message);
        this.setCode(StatusCode.SYSTEM_ERROR_CONFIGURE);
    }

    /**
     * 实例化
     * @param message 消息
     * @param cause   源异常
     */
    public ConfigureException(String message, Throwable cause) {
        super(message, cause);
        this.setCode(StatusCode.SYSTEM_ERROR_CONFIGURE);
    }

    /**
     * 实例化
     * @param cause 源异常
     */
    public ConfigureException(Throwable cause) {
        super(cause);
        this.setCode(StatusCode.SYSTEM_ERROR_CONFIGURE);
    }

    /**
     * 实例化
     * @param message            消息
     * @param cause              源异常
     * @param enableSuppression
     * @param writableStackTrace 写入跟踪
     */
    protected ConfigureException(String message, Throwable cause, boolean enableSuppression,
                                 boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.setCode(StatusCode.SYSTEM_ERROR_CONFIGURE);
    }
}
