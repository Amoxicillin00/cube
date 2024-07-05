package com.cube.cloud.core.exception;

import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

/**
 * Created with IntelliJ IDEA.
 * 异常
 * @author Long
 * @date 2023-04-14 10:24
 */
public class BaseException extends RuntimeException implements BaseError{

    private static final long serialVersionUID = -5736709556558805919L;

    /**
     * 异常码
     */
    private int code;

    /**
     * 异常等级
     */
    private ErrorLevel level = ErrorLevel.NONE;




    /**
     * 无构造实例化
     */
    public BaseException() {
        super();
    }

    /**
     * 实例化
     * @param message 消息
     */
    public BaseException(String message) {
        super(message);
    }

    /**
     * 实例化
     * @param message 消息
     * @param cause   源异常
     */
    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 实例化
     * @param cause 源异常
     */
    public BaseException(Throwable cause) {
        super(cause);
    }

    /**
     * 实例化
     * @param message            消息
     * @param cause              源异常
     * @param writableStackTrace 写入跟踪
     */
    protected BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * 格式化错误消息
     * @param format 格式
     * @param arguments 参数
     */
    public static String formatErrorMessage(String format, Object... arguments) {
        FormattingTuple formattingTuple = MessageFormatter.arrayFormat(format, arguments);
        return formattingTuple.getMessage();
    }
    

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public ErrorLevel getLevel() {
        return this.level;
    }

    @Override
    public void setLevel(ErrorLevel level) {
        this.level = level;
    }
}
