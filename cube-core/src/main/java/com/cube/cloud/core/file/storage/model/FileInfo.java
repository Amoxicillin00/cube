package com.cube.cloud.core.file.storage.model;

import cn.hutool.core.util.StrUtil;
import com.cube.cloud.core.exception.ExceptionUtils;
import com.cube.cloud.core.util.ByteUtils;
import com.cube.cloud.core.util.StringUtils;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * 文件信息
 * @author Long
 * @date 2023-01-09 12:23
 */
@Getter
@ToString(callSuper = true)
public class FileInfo implements Serializable {

    private static final long serialVersionUID = 7063500407088537360L;

    private final String name;

    private final String extensionName;

    private final String path;

    private final String fullPath;

    private final boolean file;

    private long length;

    public FileInfo(String fullPath, boolean file) {
        this(fullPath, file, 0L);
    }

    public FileInfo(String fullPath, boolean file, long length) {
        ExceptionUtils.check(StrUtil.isBlankIfStr(fullPath), "完整路径不能为空");
        this.fullPath = StringUtils.removeStart(fullPath.trim().replace("\\", "/"), '/');
        if (file) {
            int fileIndex = this.fullPath.lastIndexOf("/");
            int spotIndex = this.fullPath.lastIndexOf(".");
            if (spotIndex >= 0 && spotIndex > fileIndex) {
                this.extensionName = this.fullPath.substring(spotIndex + 1);
            } else {
                this.extensionName = "";
            }
            if (fileIndex >= 0) {
                this.name = this.fullPath.substring(fileIndex + 1);
                this.path = this.fullPath.substring(0, this.fullPath.length() - this.name.length() - 1);
            } else {
                this.path = "";
                this.name = this.fullPath;
            }
            this.length = length;
        } else {
            this.extensionName = "";
            int fileIndex = this.fullPath.lastIndexOf("/");
            if (fileIndex >= 0) {
                this.name = this.fullPath.substring(fileIndex + 1);
            } else {
                this.name = "";
            }
            this.path = this.fullPath.substring(0, this.fullPath.length() - this.name.length() - 1);
            this.length = 0L;
        }
        this.file = file;
    }


    /**
     * 设置长度
     * @param length 长度
     */
    public void setLength(long length) {
        this.length = length;
    }

    /**
     * 获取长度字符
     * @return String
     */
    public String getLengthString() {
        return ByteUtils.getSize(this.getLength());
    }
}
