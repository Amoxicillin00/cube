package com.cube.cloud.core.exception;

import com.cube.cloud.core.constants.StatusCode;

/**
 * Created with IntelliJ IDEA.
 * 参数异常
 * @author Long
 * @date 2023-04-14 10:48
 */
public class ParameterException extends SystemException{

    private static final long serialVersionUID = 5565074960769706112L;

    /**
     * 参数名称
     */
    private String paramName;



    /**
     * 无构造实例化
     */
    public ParameterException() {
        super();
        this.setCode(StatusCode.SYSTEM_ERROR_PARAMETER);
    }

    /**
     * 实例化
     * @param message 消息
     */
    public ParameterException(String message) {
        super(message);
        this.setCode(StatusCode.SYSTEM_ERROR_PARAMETER);
    }

    /**
     * 实例化
     * @param paramName 参数名称
     * @param message   消息
     */
    public ParameterException(String paramName, String message) {
        super(message);
        this.paramName = paramName;
        this.setCode(StatusCode.SYSTEM_ERROR_PARAMETER);
    }

    /**
     * 实例化
     * @param message 消息
     * @param cause   源异常
     */
    public ParameterException(String message, Throwable cause) {
        super(message, cause);
        this.setCode(StatusCode.SYSTEM_ERROR_PARAMETER);
    }

    /**
     * 实例化
     * @param paramName 参数
     * @param message   消息
     * @param cause     源异常
     */
    public ParameterException(String paramName, String message, Throwable cause) {
        super(message, cause);
        this.paramName = paramName;
        this.setCode(StatusCode.SYSTEM_ERROR_PARAMETER);
    }

    /**
     * 实例化
     * @param cause 源异常
     */
    public ParameterException(Throwable cause) {
        super(cause);
        this.setCode(StatusCode.SYSTEM_ERROR_PARAMETER);
    }

    /**
     * 实例化
     * @param message            消息
     * @param cause              源异常
     * @param enableSuppression
     * @param writableStackTrace 写入跟踪
     */
    protected ParameterException(String message, Throwable cause, boolean enableSuppression,
                                boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.setCode(StatusCode.SYSTEM_ERROR_PARAMETER);
    }

    public String getParamName() {
        return paramName;
    }
}
