package com.cube.cloud.core.file.storage.properties;

import com.cube.cloud.core.file.storage.clients.minio.MinioStorageClientProperties;
import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-01-09 15:54
 */
@ConfigurationProperties(prefix = StorageProperties.PREFIX)
@Data
@ToString(callSuper = true)
public class StorageProperties implements Serializable {

    private static final long serialVersionUID = 3360002318294196843L;

    /**
     * 属性前缀
     */
    public final static String PREFIX = "cube.storage.client";

    /**
     * minio属性
     */
    private MinioStorageClientProperties minio = new MinioStorageClientProperties();


    public StorageProperties() {
    }
}
