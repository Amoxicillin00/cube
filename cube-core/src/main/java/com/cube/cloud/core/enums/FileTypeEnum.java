package com.cube.cloud.core.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * Created with IntelliJ IDEA.
 * 文件类型枚举
 * @author Long
 * @date 2023-01-10 17:23
 */
public enum FileTypeEnum {

    IMAGE(0, "图片"),
    VIDEO(1, "视频"),
    AUDIO(2, "音频"),
    APK(3, "APK"),
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


    FileTypeEnum(int code, String message) {
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
        for (FileTypeEnum fileTypeEnum : values()) {
            if (code == fileTypeEnum.code) {
                return true;
            }
        }
        return false;
    }

    public static int getFileTypeCode(String contentType) {
        if (StringUtils.isEmpty(contentType)) {
            return FileTypeEnum.OTHER.getCode();
        }
        if (contentType.startsWith("image/")) {
            return FileTypeEnum.IMAGE.getCode();
        }
        if (contentType.startsWith("video/")) {
            return FileTypeEnum.VIDEO.getCode();
        }
        if (contentType.startsWith("audio/")) {
            return FileTypeEnum.AUDIO.getCode();
        }
        if (contentType.startsWith("application/vnd.android.package-archive")) {
            return FileTypeEnum.APK.getCode();
        }
        return FileTypeEnum.OTHER.getCode();
    }
}
