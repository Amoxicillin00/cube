package com.cube.cloud.core.exception;

import com.cube.cloud.core.constants.StatusCode;

/**
 * Created with IntelliJ IDEA.
 * 参数为空异常
 * @author Long
 * @date 2023-04-14 10:48
 */
public class ParameterNullException extends ParameterException{

    private static final long serialVersionUID = 4301424821577092981L;



    /**
     * 无构造实例化
     */
    public ParameterNullException() {
        super();
    }

    /**
     * 实例化
     * @param message 消息
     */
    public ParameterNullException(String message) {
        super(message);
    }

    /**
     * 实例化
     * @param paramName 参数名称
     * @param message   消息
     */
    public ParameterNullException(String paramName, String message) {
        super(paramName, message);
    }

    /**
     * 实例化
     * @param message 消息
     * @param cause   源异常
     */
    public ParameterNullException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 实例化
     * @param paramName 参数
     * @param message   消息
     * @param cause     源异常
     */
    public ParameterNullException(String paramName, String message, Throwable cause) {
        super(paramName, message, cause);
    }

    /**
     * 实例化
     * @param cause 源异常
     */
    public ParameterNullException(Throwable cause) {
        super(cause);
    }

    /**
     * 实例化
     * @param message            消息
     * @param cause              源异常
     * @param enableSuppression
     * @param writableStackTrace 写入跟踪
     */
    protected ParameterNullException(String message, Throwable cause, boolean enableSuppression,
                                    boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }



    @Override
    public final int getCode() {
        return StatusCode.SYSTEM_ERROR_PARAMETER_NULL;
    }

    @Override
    public final void setCode(int code) {
    }
}
