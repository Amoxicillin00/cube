package com.cube.cloud.core.enums;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-06-20 16:21
 */
public enum AuthorizeEnum {

    NOT_NEED(0, "不需要"),
    NEED(1, "需要"),
    ;

    /**
     * 编码
     */
    private int code;

    /**
     * 信息
     */
    private String message;


    AuthorizeEnum(int code, String message) {
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
        for (AuthorizeEnum authorizeEnum : values()) {
            if (code == authorizeEnum.code) {
                return true;
            }
        }
        return false;
    }
}
