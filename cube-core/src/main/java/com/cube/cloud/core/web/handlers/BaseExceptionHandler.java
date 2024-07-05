package com.cube.cloud.core.web.handlers;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import com.cube.cloud.core.exception.BaseException;
import com.cube.cloud.core.web.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 * 捕获异常
 * @author Long
 * @date 2023-06-16 11:30
 */
@Slf4j
@RestControllerAdvice
public class BaseExceptionHandler {

    /**
     * 异常（500）
     * @param request 请求信息
     * @param response 响应信息
     * @param throwable 异常信息
     * @return 对象信息
     */
    @ExceptionHandler(value = Throwable.class)
    public Object exceptionResponse(HttpServletRequest request, HttpServletResponse response, Throwable throwable) {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        sendMessage(request, throwable);
        return BaseResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "系统错误，请联系管理员");
    }

    /**
     * 未登录异常（401）
     * @param request 请求信息
     * @param response 响应信息
     * @param throwable 异常信息
     * @return 对象信息
     */
    @ExceptionHandler(value = NotLoginException.class)
    public Object exceptionResponse(HttpServletRequest request, HttpServletResponse response, NotLoginException throwable) {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        sendMessage(request, throwable);
        return BaseResponse.error(HttpStatus.UNAUTHORIZED.value(), "用户未登录");
    }

    /**
     * 无权操作异常（403）
     * @param request 请求信息
     * @param response 响应信息
     * @param throwable 异常信息
     * @return 对象信息
     */
    @ExceptionHandler(value = NotPermissionException.class)
    public Object exceptionResponse(HttpServletRequest request, HttpServletResponse response, NotPermissionException throwable) {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        sendMessage(request, throwable);
        return BaseResponse.error(HttpStatus.FORBIDDEN.value(), throwable.getMessage());
    }

    /**
     * 输入参数检验异常（400）
     * @param throwable 异常信息
     * @param response 响应信息
     * @return 对象信息
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object exceptionResponse(HttpServletRequest request, HttpServletResponse response, MethodArgumentNotValidException throwable) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        String message = Objects.requireNonNull(throwable.getBindingResult().getFieldError()).getDefaultMessage();
        sendMessage(request, throwable);
        return BaseResponse.error(HttpStatus.BAD_REQUEST.value(), message);
    }

    /**
     * 自定义异常（500）
     * @param request 请求信息
     * @param response 响应信息
     * @param throwable 异常信息
     * @return 对象信息
     */
    @ExceptionHandler(value = BaseException.class)
    public Object exceptionResponse(HttpServletRequest request, HttpServletResponse response, BaseException throwable) {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        sendMessage(request, throwable);
        return BaseResponse.error(throwable.getCode(), throwable.getMessage());
    }

    /**
     * 日志打印异常信息
     */
    public void sendMessage(HttpServletRequest request, Throwable throwable) {
        // 本地日志打印
        String message = throwable.getMessage();
        String format = "\n请求地址：%s\n请求方式：%s\n发生异常：%s\n异常信息：%s";
        String exception = String.format(format, request.getRequestURI(), request.getMethod(),  throwable.getClass().getName(), message);
        log.error(exception, throwable);
    }

    /**
     * 获取状态码
     * @param request 请求信息
     * @return 状态码信息
     */
    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        try {
            return HttpStatus.valueOf(statusCode);
        }
        catch (Exception ex) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
