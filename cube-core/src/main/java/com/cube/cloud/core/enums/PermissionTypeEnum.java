package com.cube.cloud.core.enums;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-06-20 16:20
 */
public enum PermissionTypeEnum {

    MENU(0, "菜单"),
    BUTTON(1, "按钮"),
    PERMISSION(2, "权限"),
    ;

    /**
     * 编码
     */
    private int code;

    /**
     * 信息
     */
    private String message;


    PermissionTypeEnum(int code, String message) {
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
        for (PermissionTypeEnum permissionTypeEnum : values()) {
            if (code == permissionTypeEnum.code) {
                return true;
            }
        }
        return false;
    }
}
