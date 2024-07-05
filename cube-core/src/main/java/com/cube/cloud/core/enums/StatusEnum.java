package com.cube.cloud.core.enums;

/**
 * Created with IntelliJ IDEA.
 * 状态枚举
 * @author Long
 * @date 2023-05-22 11:40
 */
public enum StatusEnum {

    DISABLE(0, "禁用/失败"),
    ENABLE(1, "启用/成功"),
    ;

    /**
     * 编码
     */
    private int code;

    /**
     * 信息
     */
    private String message;


    StatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static boolean hasCode(int code) {
        for (StatusEnum statusEnum : values()) {
            if (code == statusEnum.code) {
                return true;
            }
        }
        return false;
    }
}
