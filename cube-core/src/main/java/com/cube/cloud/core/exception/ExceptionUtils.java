package com.cube.cloud.core.exception;

import cn.hutool.core.util.StrUtil;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created with IntelliJ IDEA.
 * 异常工具
 * @author Long
 * @date 2023-04-14 10:19
 */
public class ExceptionUtils {


    /**
     * 输出跟踪异常
     * @param error 异常
     * @return String
     */
    public static String toStackTraceString(Throwable error) {
        StringWriter stringWriter = new StringWriter();
        error.printStackTrace(new PrintWriter(stringWriter, true));
        return stringWriter.getBuffer().toString();
    }

    /**
     * 非空检查
     * @param value 参数值
     * @param <T> 泛型
     */
    public static <T> T checkNotNull(T value) {
        if (value == null) {
            throw new ParameterNullException("参数值为 null");
        }
        return value;
    }

    /**
     * 非空检查
     * @param value 参数值
     * @param paramName 参数名
     * @param <T> 泛型
     */
    public static <T> T checkNotNull(T value, String paramName) {
        if (value == null) {
            throw new ParameterNullException(paramName, String.format("参数 %s 的值为 null", paramName));
        }
        return value;
    }

    /**
     * 非空以及非空白检查
     * @param value 参数值
     */
    public static String checkNotNullOrBlank(String value) {
        checkNotNull(value);
        if (StrUtil.isBlank(value)) {
            throw new ParameterBlankException("参数值为空");
        }
        return value;
    }

    /**
     * 非空和非空白检查
     * @param value 参数值
     * @param paramName 参数名
     */
    public static String checkNotNullOrBlank(String value, String paramName) {
        checkNotNull(value, paramName);
        if (StrUtil.isBlank(value)) {
            throw new ParameterBlankException(paramName, String.format("参数 %s 的值为空", paramName));
        }
        return value;
    }

    /**
     * 检查异常
     * @param expression 布尔值
     * @param message 异常信息
     */
    public static void check(boolean expression, String message) {
        if (expression) {
            ExceptionUtils.throwValidationException(message);
        }
    }

    /**
     * 抛出 ValidationException 异常
     * @param message 异常信息
     * @return ValidationException
     */
    public static ValidationException throwValidationException(String message) {
        return throwValidationException(message, null);
    }

    /**
     * 抛出 ValidationException 异常
     * @param message 异常信息
     * @param cause 异常源
     * @return ValidationException
     */
    public static ValidationException throwValidationException(String message, Throwable cause) {
        throw new ValidationException(message, cause);
    }


    /**
     * 抛出 BaseException 异常
     * @param message 异常信息
     * @return BaseException
     */
    public static BaseException throwBaseException(String message) {
        return throwBaseException(message, null);
    }

    /**
     * 抛出 BaseException 异常
     * @param message 异常信息
     * @param cause 异常源
     * @return BaseException
     */
    public static BaseException throwBaseException(String message, Throwable cause) {
        throw new BaseException(message, cause);
    }

    /**
     * 抛出 BaseException 异常
     * @param message 异常信息
     * @param errorCode 异常代码
     * @param errorLevel 异常等级
     * @return BaseException
     */
    public static BaseException throwBaseException(String message, int errorCode, ErrorLevel errorLevel) {
        return throwBaseException(message, errorCode, errorLevel, null);
    }

    /**
     * 抛出 BaseException 异常
     * @param message 异常信息
     * @param errorCode 异常代码
     * @param errorLevel 异常等级
     * @param cause 异常源
     * @return BaseException
     */
    public static BaseException throwBaseException(String message, int errorCode, ErrorLevel errorLevel,
                                                   Throwable cause) {
        BaseException exception = new BaseException(message, cause);
        exception.setCode(errorCode);
        exception.setLevel(errorLevel);
        throw exception;
    }


    /**
     * 抛出 DefaultException 异常
     * @param message 异常信息
     * @return DefaultException
     */
    public static DefaultException throwDefaultException(String message) {
        return throwDefaultException(message, null);
    }

    /**
     * 抛出 DefaultException 异常
     * @param message 异常信息
     * @param cause 异常源
     * @return DefaultException
     */
    public static DefaultException throwDefaultException(String message, Throwable cause) {
        throw new DefaultException(message, cause);
    }

    /**
     * 抛出 DefaultException 异常
     * @param message 异常信息
     * @param errorCode 异常代码
     * @return DefaultException
     */
    public static DefaultException throwDefaultException(String message, int errorCode) {
        return throwDefaultException(message, errorCode, null);
    }

    /**
     * 抛出 DefaultException 异常
     * @param message 异常信息
     * @param errorCode 异常代码
     * @param cause 异常源
     * @return DefaultException
     */
    public static DefaultException throwDefaultException(String message, int errorCode, Throwable cause) {
        DefaultException exception = new DefaultException(message, cause);
        exception.setCode(errorCode);
        throw exception;
    }


    /**
     * 抛出 ApplicationException 异常
     * @param message 异常信息
     * @return ApplicationException
     */
    public static ApplicationException throwApplicationException(String message) {
        return throwApplicationException(message, null);
    }

    /**
     * 抛出 ApplicationException 异常
     * @param message 异常信息
     * @param cause 异常源
     * @return ApplicationException
     */
    public static ApplicationException throwApplicationException(String message, Throwable cause) {
        throw new ApplicationException(message, cause);
    }

    /**
     * 抛出 ApplicationException 异常
     * @param message 异常信息
     * @param errorCode 异常代码
     * @return ApplicationException
     */
    public static ApplicationException throwApplicationException(String message, int errorCode) {
        return throwApplicationException(message, errorCode, null);
    }

    /**
     * 抛出 ApplicationException 异常
     * @param message 异常信息
     * @param errorCode 异常代码
     * @param cause 异常源
     * @return ApplicationException
     */
    public static ApplicationException throwApplicationException(String message, int errorCode, Throwable cause) {
        ApplicationException exception = new ApplicationException(message, cause);
        exception.setCode(errorCode);
        throw exception;
    }


    /**
     * 抛出 SystemException 异常
     * @param message 异常信息
     * @return SystemException
     */
    public static SystemException throwSystemException(String message) {
        return throwSystemException(message, null);
    }

    /**
     * 抛出 SystemException 异常
     * @param message 异常信息
     * @param cause 异常源
     * @return SystemException
     */
    public static SystemException throwSystemException(String message, Throwable cause) {
        throw new ApplicationException(message, cause);
    }

    /**
     * 抛出 SystemException 异常
     * @param message 异常信息
     * @param errorCode 异常代码
     * @return SystemException
     */
    public static SystemException throwSystemException(String message, int errorCode) {
        return throwSystemException(message, errorCode, null);
    }

    /**
     * 抛出 SystemException 异常
     * @param message 异常信息
     * @param errorCode 异常代码
     * @param cause 异常源
     * @return SystemException
     */
    public static SystemException throwSystemException(String message, int errorCode, Throwable cause) {
        ApplicationException exception = new ApplicationException(message, cause);
        exception.setCode(errorCode);
        throw exception;
    }


    /**
     * 抛出 UnSupportException 异常
     * @param message 异常信息
     * @return UnSupportException
     */
    public static UnSupportException throwUnSupportException(String message) {
        return throwUnSupportException(message, null);
    }

    /**
     * 抛出 UnSupportException 异常
     * @param message 异常信息
     * @param cause 异常源
     * @return UnSupportException
     */
    public static UnSupportException throwUnSupportException(String message, Throwable cause) {
        throw new UnSupportException(message, cause);
    }

    /**
     * 抛出 InvalidCastException 异常
     * @param message 异常信息
     * @return InvalidCastException
     */
    public static InvalidCastException throwInvalidCastException(String message) {
        return throwInvalidCastException(message, null);
    }

    /**
     * 抛出 InvalidCastException 异常
     * @param message 异常信息
     * @param cause 异常源
     * @return InvalidCastException
     */
    public static InvalidCastException throwInvalidCastException(String message, Throwable cause) {
        throw new InvalidCastException(message, cause);
    }

    /**
     * 抛出 OverflowException 异常
     * @param message 异常信息
     * @return OverflowException
     */
    public static OverflowException throwOverflowException(String message) {
        return throwOverflowException(message, null);
    }

    /**
     * 抛出 OverflowException 异常
     * @param message 异常信息
     * @param cause 异常源
     * @return OverflowException
     */
    public static OverflowException throwOverflowException(String message, Throwable cause) {
        throw new OverflowException(message, cause);
    }

    /**
     * 抛出 FormatException 异常
     * @param message 异常信息
     * @return FormatException
     */
    public static FormatException throwFormatException(String message) {
        return throwFormatException(message, null);
    }

    /**
     * 抛出 FormatException 异常
     * @param message 异常信息
     * @param cause 异常源
     * @return FormatException
     */
    public static FormatException throwFormatException(String message, Throwable cause) {
        throw new FormatException(message, cause);
    }

    /**
     * 抛出 SignException 异常
     * @return SignException
     */
    public static SignException throwSingException() {
        return throwSingException("签名不正确");
    }

    /**
     * 抛出 SignException 异常
     * @param message 异常信息
     * @return SignException
     */
    public static SignException throwSingException(String message) {
        return throwSingException(message, null);
    }

    /**
     * 抛出 SignException 异常
     * @param message 异常信息
     * @param cause 异常源
     * @return SignException
     */
    public static SignException throwSingException(String message, Throwable cause) {
        throw new SignException(message, cause);
    }

    /**
     * 抛出 ParameterException 异常
     * @param paramName 参数名
     * @param message 异常信息
     * @return ParameterException
     */
    public static ParameterException throwParameterException(String paramName, String message) {
        return throwParameterException(paramName, message, null);
    }

    /**
     * 抛出 ParameterException 异常
     * @param paramName 参数名
     * @param message 异常信息
     * @param cause 异常源
     * @return ParameterException
     */
    public static ParameterException throwParameterException(String paramName, String message, Throwable cause) {
        throw new ParameterException(paramName, message, cause);
    }

    /**
     * 抛出 ParameterNullException 异常
     * @param paramName 参数名
     * @param message 异常信息
     * @return ParameterNullException
     */
    public static ParameterNullException throwParameterNullException(String paramName, String message) {
        return throwParameterNullException(paramName, message, null);
    }

    /**
     * 抛出 ParameterNullException 异常
     * @param paramName 参数名
     * @param message 异常信息
     * @param cause 异常源
     * @return ParameterNullException
     */
    public static ParameterNullException throwParameterNullException(String paramName, String message, Throwable cause) {
        throw new ParameterNullException(paramName, message, cause);
    }

    /**
     * 抛出 ParameterBlankException 异常
     * @param paramName 参数名
     * @param message 异常信息
     * @return ParameterBlankException
     */
    public static ParameterBlankException throwParameterBlankException(String paramName, String message) {
        return throwParameterBlankException(paramName, message, null);
    }

    /**
     * 抛出 ParameterBlankException 异常
     * @param paramName 参数名
     * @param message 异常信息
     * @param cause 异常源
     * @return ParameterBlankException
     */
    public static ParameterBlankException throwParameterBlankException(String paramName, String message,
                                                                     Throwable cause) {
        throw new ParameterBlankException(paramName, message, cause);
    }

    /**
     * 抛出 ParameterOverflowException 异常
     * @param paramName 参数名
     * @param message 异常信息
     * @return ParameterOverflowException
     */
    public static ParameterOverflowException throwParameterOverflowException(String paramName, String message) {
        return throwParameterOverflowException(paramName, message, null);
    }

    /**
     * 抛出 ParameterOverflowException 异常
     * @param paramName 参数名
     * @param message 异常信息
     * @param cause 异常源
     * @return ParameterOverflowException
     */
    public static ParameterOverflowException throwParameterOverflowException(String paramName, String message,
                                                                           Throwable cause) {
        throw new ParameterOverflowException(paramName, message, cause);
    }

    /**
     * 抛出 ConfigureException 异常
     * @param message 异常信息
     * @return ConfigureException
     */
    public static ConfigureException throwConfigureException(String message) {
        return throwConfigureException(message, null);
    }

    /**
     * 抛出 ConfigureException 异常
     * @param message 异常信息
     * @param cause 异常源
     * @return ConfigureException
     */
    public static ConfigureException throwConfigureException(String message, Throwable cause) {
        throw new ConfigureException(message, cause);
    }

    /**
     * 抛出 ConfigureException 异常
     * @param message 异常信息
     * @param errorCode 异常代码
     * @return ConfigureException
     */
    public static ConfigureException throwConfigureException(String message, int errorCode) {
        return throwConfigureException(message, errorCode, null);
    }

    /**
     * 抛出 ConfigureException 异常
     * @param message 异常信息
     * @param errorCode 异常代码
     * @param cause 异常源
     * @return ConfigureException
     */
    public static ConfigureException throwConfigureException(String message, int errorCode, Throwable cause) {
        ConfigureException exception = new ConfigureException(message, cause);
        exception.setCode(errorCode);
        throw exception;
    }
}
