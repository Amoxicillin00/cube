package com.cube.cloud.core.web.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.cube.cloud.core.exception.BaseError;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * Created with IntelliJ IDEA.
 * 统一结果响应体
 * @author Long
 * @date 2023-04-13 15:22
 */
@Data
public class BaseResponse<T> {

    /**
     * 状态码
     */
    @JSONField(ordinal = 1)
    private int code;

    /**
     * 具体信息
     */
    @JSONField(ordinal = 2)
    private String message;

    /**
     * 数据
     */
    @JSONField(ordinal = 3)
    private T data;


    /**
     * 成功
     * @param value 值
     * @param <T> 泛型
     */
    public static <T> BaseResponse<T> success(T value) {
        BaseResponse<T> response = success();
        response.setData(value);
        return response;
    }

    /**
     * 成功
     * @param <T> 泛型
     */
    public static <T> BaseResponse<T> success() {
        BaseResponse<T> response = new BaseResponse<>();
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Success");
        return response;
    }


    /**
     * 错误异常
     * @param code 错误码
     * @param message 错误信息
     * @param <T> 泛型
     */
    public static <T> BaseResponse<T> error(int code, String message) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setCode(code);
        response.setData(null);
        response.setMessage(message);
        return response;
    }

    /**
     * 错误异常
     * @param message 错误信息
     * @param <T> 泛型
     */
    public static <T> BaseResponse<T> error(String message) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setData(null);
        response.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        return response;
    }

    /**
     * 错误异常
     * @param error 错误信息
     * @param <T> 泛型
     */
    public static <T> BaseResponse<T> error(Exception error) {
        if (error instanceof BaseError) {
            BaseError baseError = (BaseError) error;
            return error(baseError.getCode(), error.getMessage());
        }
        return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), error.getMessage());
    }
}
