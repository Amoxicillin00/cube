package com.cube.cloud.core.exception;

import com.cube.cloud.core.constants.StatusCode;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-04-14 11:24
 */
public class ApplicationException extends BaseException{

    private static final long serialVersionUID = 8228509560013808946L;


    /**
     * 无构造实例化
     */
    public ApplicationException() {
        super();
        this.setCode(StatusCode.APPLICATION_ERROR);
    }

    /**
     * 实例化
     * @param message 消息
     */
    public ApplicationException(String message) {
        super(message);
        this.setCode(StatusCode.APPLICATION_ERROR);
    }

    /**
     * 实例化
     * @param message 消息
     * @param cause   源异常
     */
    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
        this.setCode(StatusCode.APPLICATION_ERROR);
    }

    /**
     * 实例化
     * @param cause 源异常
     */
    public ApplicationException(Throwable cause) {
        super(cause);
        this.setCode(StatusCode.APPLICATION_ERROR);
    }

    /**
     * 实例化
     * @param message            消息
     * @param cause              源异常
     * @param enableSuppression
     * @param writableStackTrace 写入跟踪
     */
    protected ApplicationException(String message, Throwable cause, boolean enableSuppression,
                                   boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.setCode(StatusCode.APPLICATION_ERROR);
    }


    @Override
    public final ErrorLevel getLevel() {
        return ErrorLevel.APPLICATION;
    }

    @Override
    public final void setLevel(ErrorLevel level) {
    }
}
