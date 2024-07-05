package com.cube.cloud.core.exception;

import com.cube.cloud.core.constants.StatusCode;

/**
 * Created with IntelliJ IDEA.
 * 系统异常
 * @author Long
 * @date 2023-04-14 10:21
 */
public class SystemException extends BaseException{

    private static final long serialVersionUID = -3119533031067105326L;


    /**
     * 无构造实例化
     */
    public SystemException() {
        super();
        this.setCode(StatusCode.SYSTEM_ERROR);
    }

    /**
     * 实例化
     * @param message 消息
     */
    public SystemException(String message) {
        super(message);
        this.setCode(StatusCode.SYSTEM_ERROR);
    }

    /**
     * 实例化
     * @param message 消息
     * @param cause   源异常
     */
    public SystemException(String message, Throwable cause) {
        super(message, cause);
        this.setCode(StatusCode.SYSTEM_ERROR);
    }

    /**
     * 实例化
     * @param cause 源异常
     */
    public SystemException(Throwable cause) {
        super(cause);
        this.setCode(StatusCode.SYSTEM_ERROR);
    }

    /**
     * 实例化
     * @param message            消息
     * @param cause              源异常
     * @param enableSuppression
     * @param writableStackTrace 写入跟踪
     */
    protected SystemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.setCode(StatusCode.SYSTEM_ERROR);
    }

    @Override
    public final ErrorLevel getLevel() {
        return ErrorLevel.SYSTEM;
    }

    @Override
    public final void setLevel(ErrorLevel level) {
    }
}
