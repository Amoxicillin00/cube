package com.cube.cloud.core.log;


/**
 * Created with IntelliJ IDEA.
 * 日志操作类型(0 : 登录、1 : 添加、2 : 删除、3 : 修改、4 : 查询、5 : 导入、6 : 导出、99  : 默认)
 * @author Long
 * @date 2023-08-24 16:05
 */
public enum OperateType {

    LOGIN(0, "登录"),
    CREATE(1, "添加"),
    DELETE(2, "删除"),
    UPDATE(3, "修改"),
    SELECT(4, "查询"),
    IMPORT(5, "导入"),
    EXPORT(6, "导出"),
    UPLOAD(7, "上传文件"),
    LOGOUT(10, "退出"),
    OTHER(99, "其他"),
    ;

    /**
     * 编码
     */
    private int code;

    /**
     * 信息
     */
    private String message;


    OperateType(int code, String message) {
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
        for (OperateType operateType : values()) {
            if (code == operateType.code) {
                return true;
            }
        }
        return false;
    }

    public static OperateType parse(int code) {
        for (OperateType operateType : values()) {
            if (code == operateType.code) {
                return operateType;
            }
        }
        return null;
    }
}
