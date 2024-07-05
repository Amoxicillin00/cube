package com.cube.cloud.core.exception;

/**
 * Created with IntelliJ IDEA.
 * 超载异常
 * @author Long
 * @date 2023-04-14 11:45
 */
public class OverflowException extends SystemException{

    private static final long serialVersionUID = -5997102748813761165L;


    /**
     * 无构造实例化
     */
    public OverflowException() {
        super();
    }

    /**
     * 实例化
     * @param message 消息
     */
    public OverflowException(String message) {
        super(message);
    }

    /**
     * 实例化
     * @param message 消息
     * @param cause   源异常
     */
    public OverflowException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 实例化
     * @param cause 源异常
     */
    public OverflowException(Throwable cause) {
        super(cause);
    }

    /**
     * 实例化
     * @param message            消息
     * @param cause              源异常
     * @param enableSuppression
     * @param writableStackTrace 写入跟踪
     */
    protected OverflowException(String message, Throwable cause, boolean enableSuppression,
                                boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
