package com.cube.cloud.core.exception;

import com.cube.cloud.core.constants.StatusCode;

/**
 * Created with IntelliJ IDEA.
 * 默认异常
 * @author Long
 * @date 2023-04-14 11:18
 */
public class DefaultException extends BaseException{

    private static final long serialVersionUID = 7458566418864389388L;


    /**
     * 无构造实例化
     */
    public DefaultException() {
        super();
        this.setCode(StatusCode.ERROR);
    }

    /**
     * 实例化
     * @param message 消息
     */
    public DefaultException(String message) {
        super(message);
        this.setCode(StatusCode.ERROR);
    }

    /**
     * 实例化
     * @param message 消息
     * @param cause   源异常
     */
    public DefaultException(String message, Throwable cause) {
        super(message, cause);
        this.setCode(StatusCode.ERROR);
    }

    /**
     * 实例化
     * @param cause 源异常
     */
    public DefaultException(Throwable cause) {
        super(cause);
        this.setCode(StatusCode.ERROR);
    }

    /**
     * 实例化
     * @param message            消息
     * @param cause              源异常
     * @param enableSuppression
     * @param writableStackTrace 写入跟踪
     */
    protected DefaultException(String message, Throwable cause, boolean enableSuppression,
                                    boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.setCode(StatusCode.ERROR);
    }



    @Override
    public final ErrorLevel getLevel() {
        return ErrorLevel.USER;
    }

    @Override
    public final void setLevel(ErrorLevel level) {
    }
}
