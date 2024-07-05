package com.cube.cloud.core.file.storage.properties;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * 存储抽象属性
 * @author Long
 * @date 2023-01-09 15:49
 */
@Data
@ToString(callSuper = true)
public class AbstractStorageClientProperties implements Serializable {

    private static final long serialVersionUID = 6189112449778004440L;

    /**
     * 通道 Bean 前缀
     */
    public static final String CHANNEL_BEAN_PREFIX = "cube";

    /**
     * 通道 Bean 后缀
     */
    public static final String CHANNEL_BEAN_SUFFIX = "StorageClient";

    /**
     * 是否启用
     */
    private boolean enable = false;

    /**
     * 终节点
     */
    private String endpoint;

    /**
     * 默认分区
     */
    private String defaultBucketName;

    /**
     * 读数据块大小
     */
    private int readBlockSize = 2048;

    /**
     * 写数据块大小
     */
    private int writeBlockSize = 2048;

    /**
     * 访问键
     */
    private String accessKey = "";

    /**
     * 安全键
     */
    private String secretKey = "";
}
