package com.cube.cloud.core.enums;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-05-30 15:39
 */
public enum DataScopeEnum {

    ALL(0, "全部数据"),
    CUSTOM(1, "自定义数据"),
    ;

    /**
     * 编码
     */
    private int code;

    /**
     * 信息
     */
    private String message;


    DataScopeEnum(int code, String message) {
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
        for (DataScopeEnum dataScopeEnum : values()) {
            if (code == dataScopeEnum.code) {
                return true;
            }
        }
        return false;
    }
}
