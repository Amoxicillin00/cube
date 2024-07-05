package com.cube.cloud.core.file.storage.clients.minio;

import com.cube.cloud.core.file.storage.properties.AbstractStorageClientProperties;
import com.cube.cloud.core.file.storage.properties.StorageProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Created with IntelliJ IDEA.
 * Minio 存储客户端属性
 * @author Long
 * @date 2023-01-10 12:03
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class MinioStorageClientProperties extends AbstractStorageClientProperties {

    private static final long serialVersionUID = 5071486347875342457L;

    /**
     * bean条件属性
     */
    public static final String BEAN_CONDITIONAL_PROPERTY = StorageProperties.PREFIX + ".minio.enable";

    /**
     * 通道 Bean 名称
     */
    public static final String CHANNEL_BEAN_NAME = CHANNEL_BEAN_PREFIX + "Minio" + CHANNEL_BEAN_SUFFIX;

    /**
     * 服务器地址
     */
    public static final String MINIO_SERVER_URL = StorageProperties.PREFIX + ".minio.serverUrl";

    /**
     * 服务器地址
     */
    private String serverUrl = MinioStorageClientProperties.MINIO_SERVER_URL;
}
