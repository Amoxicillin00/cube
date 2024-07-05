package com.cube.cloud.core.exception;

/**
 * Created with IntelliJ IDEA.
 * 错误抽象
 * @author Long
 * @date 2023-04-13 15:58
 */
public interface BaseError {

    int getCode();

    void setCode(int code);

    String getMessage();

    ErrorLevel getLevel();

    void setLevel(ErrorLevel level);
}
