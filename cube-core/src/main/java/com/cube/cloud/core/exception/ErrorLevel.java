package com.cube.cloud.core.exception;

/**
 * Created with IntelliJ IDEA.
 * 异常等级
 * @author Long
 * @date 2023-04-13 16:00
 */
public enum ErrorLevel {


    /**
     * 未知
     */
    NONE(0),

    /**
     * 用户级别
     */
    USER(1),

    /**
     * 应用级别
     */
    APPLICATION(2),

    /**
     * 系统级别
     */
    SYSTEM(3);


    private final int code;



    private ErrorLevel(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
