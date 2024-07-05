package com.cube.cloud.core.enums;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-06-20 16:21
 */
public enum ResourceClientTypeEnum {

    WEB(0, "web管理端"),
    APP(1, "app客户端"),
    H5(2, "h5小程序"),
    ;

    /**
     * 编码
     */
    private int code;

    /**
     * 信息
     */
    private String message;


    ResourceClientTypeEnum(int code, String message) {
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
        for (ResourceClientTypeEnum resourceClientTypeEnum : values()) {
            if (code == resourceClientTypeEnum.code) {
                return true;
            }
        }
        return false;
    }
}
