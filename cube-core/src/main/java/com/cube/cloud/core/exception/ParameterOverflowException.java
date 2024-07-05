package com.cube.cloud.core.exception;

import com.cube.cloud.core.constants.StatusCode;

/**
 * Created with IntelliJ IDEA.
 *参数超载异常
 * @author Long
 * @date 2023-04-14 10:49
 */
public class ParameterOverflowException extends ParameterException{

    private static final long serialVersionUID = -4155960502770234431L;

    /**
     * 参数值
     */
    private Object value;



    /**
     * 无构造实例化
     */
    public ParameterOverflowException() {
        super();
    }

    /**
     * 实例化
     * @param message 消息
     */
    public ParameterOverflowException(String message) {
        super(message);
    }

    /**
     * 实例化
     * @param paramName 参数名称
     * @param message   消息
     */
    public ParameterOverflowException(String paramName, String message) {
        super(paramName, message);
    }

    /**
     * 实例化
     * @param paramName   参数名称
     * @param message     消息
     * @param value 导致异常的参数值
     */
    public ParameterOverflowException(String paramName, String message, Object value) {
        super(paramName, message);
        this.value = value;
    }

    /**
     * 实例化
     * @param message 消息
     * @param cause   源异常
     */
    public ParameterOverflowException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 实例化
     * @param paramName 参数
     * @param message   消息
     * @param cause     源异常
     */
    public ParameterOverflowException(String paramName, String message, Throwable cause) {
        super(paramName, message, cause);
    }

    /**
     * 实例化
     *
     * @param paramName   参数
     * @param message     消息
     * @param value 导致异常的参数值
     * @param cause       源异常
     */
    public ParameterOverflowException(String paramName, String message, Object value, Throwable cause) {
        super(paramName, message, cause);
        this.value = value;
    }

    /**
     * 实例化
     * @param cause 源异常
     */
    public ParameterOverflowException(Throwable cause) {
        super(cause);
    }

    /**
     * 实例化
     * @param message            消息
     * @param cause              源异常
     * @param enableSuppression
     * @param writableStackTrace 写入跟踪
     */
    protected ParameterOverflowException(String message, Throwable cause, boolean enableSuppression,
                                        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


    public Object getValue() {
        return value;
    }

    @Override
    public final int getCode() {
        return StatusCode.SYSTEM_ERROR_PARAMETER_OVERFLOW;
    }

    @Override
    public final void setCode(int code) {
    }
}
