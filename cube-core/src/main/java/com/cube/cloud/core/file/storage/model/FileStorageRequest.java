package com.cube.cloud.core.file.storage.model;

import cn.hutool.core.util.StrUtil;
import com.cube.cloud.core.exception.ExceptionUtils;
import com.cube.cloud.core.util.StringUtils;
import lombok.Getter;
import lombok.ToString;

import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * 文件存储请求
 * @author Long
 * @date 2023-01-09 14:44
 */
@Getter
@ToString(callSuper = true)
public class FileStorageRequest {

    /**
     * 分区名称
     */
    private final String bucketName;

    /**
     * 输入流
     */
    private final InputStream inputStream;

    /**
     * 文件信息
     */
    private final FileInfo fileInfo;


    public FileStorageRequest(String bucketName, InputStream inputStream, String fullPath) {
        this(bucketName, inputStream, new FileInfo(fullPath, true, 0L));
    }

    public FileStorageRequest(String bucketName, InputStream inputStream, FileInfo fileInfo) {
        ExceptionUtils.check(StrUtil.isBlankIfStr(bucketName), "分区名称不能为空");
        ExceptionUtils.check(StrUtil.isBlankIfStr(fileInfo), "文件信息不能为空");
        ExceptionUtils.check(StrUtil.isBlankIfStr(inputStream), "输入流不能为空");

        this.fileInfo = fileInfo;
        this.bucketName = bucketName;
        this.inputStream = inputStream;
    }
}
