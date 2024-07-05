package com.cube.cloud.core.exception;

import com.cube.cloud.core.constants.StatusCode;

/**
 * Created with IntelliJ IDEA.
 * 参数为空异常
 * @author Long
 * @date 2023-04-14 10:49
 */
public class ParameterBlankException extends ParameterException{

    private static final long serialVersionUID = 1485402880646329395L;



    /**
     * 无构造实例化
     */
    public ParameterBlankException() {
        super();
    }

    /**
     * 实例化
     * @param message 消息
     */
    public ParameterBlankException(String message) {
        super(message);
    }

    /**
     * 实例化
     * @param paramName 参数名称
     * @param message   消息
     */
    public ParameterBlankException(String paramName, String message) {
        super(paramName, message);
    }

    /**
     * 实例化
     * @param message 消息
     * @param cause   源异常
     */
    public ParameterBlankException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 实例化
     * @param paramName 参数
     * @param message   消息
     * @param cause     源异常
     */
    public ParameterBlankException(String paramName, String message, Throwable cause) {
        super(paramName, message, cause);
    }

    /**
     * 实例化
     * @param cause 源异常
     */
    public ParameterBlankException(Throwable cause) {
        super(cause);
    }

    /**
     * 实例化
     *
     * @param message            消息
     * @param cause              源异常
     * @param enableSuppression
     * @param writableStackTrace 写入跟踪
     */
    protected ParameterBlankException(String message, Throwable cause, boolean enableSuppression,
                                     boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


    @Override
    public final int getCode() {
        return StatusCode.SYSTEM_ERROR_PARAMETER_BLANK;
    }

    @Override
    public final void setCode(int code) {
    }
}
