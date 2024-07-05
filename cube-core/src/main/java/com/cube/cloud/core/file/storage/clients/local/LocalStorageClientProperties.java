package com.cube.cloud.core.file.storage.clients.local;

import com.cube.cloud.core.file.storage.properties.AbstractStorageClientProperties;
import com.cube.cloud.core.file.storage.properties.StorageProperties;
import com.cube.cloud.core.util.StringUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-01-10 14:48
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class LocalStorageClientProperties extends AbstractStorageClientProperties {

    private static final long serialVersionUID = -3278925505001084096L;

    /**
     * bean条件属性
     */
    public static final String BEAN_CONDITIONAL_PROPERTY = StorageProperties.PREFIX + ".local.enable";

    /**
     * 通道 Bean 名称
     */
    public static final String CHANNEL_BEAN_NAME = CHANNEL_BEAN_PREFIX + "Local" + CHANNEL_BEAN_SUFFIX;

    /**
     * 默认终节点
     */
    public static final String DEFAULT_ENDPOINT = "/";

    /**
     * 默认分区
     */
    public static final String DEFAULT_BUCKET_NAME = "files";

    /**
     * 根文件路径
     */
    private String rootFilePath = "";



    public LocalStorageClientProperties() {
        this.setEndpoint(DEFAULT_ENDPOINT);
        this.setRootFilePath("");
        this.setDefaultBucketName(DEFAULT_BUCKET_NAME);
    }

    /**
     * 初始化属性
     */
    public void initByProperties() {
        if (StringUtils.isNullOrBlank(this.getEndpoint())) {
            this.setEndpoint(LocalStorageClientProperties.DEFAULT_ENDPOINT);
        }
        if (StringUtils.isNullOrBlank(this.getDefaultBucketName())) {
            this.setDefaultBucketName(LocalStorageClientProperties.DEFAULT_BUCKET_NAME);
        }
        if (StringUtils.isNullOrBlank(this.getRootFilePath())) {
            this.setRootFilePath(LocalStorageClient.getDefaultLocalPath());
        }
        if (StringUtils.isNullOrBlank(this.getRootFilePath())) {
            //TODO 抛出异常
            System.out.println("抛出异常！本地文件存储无默认保存路径 rootFilePath");
            //ExceptionUtils.throwConfigureException("本地文件存储无默认保存路径 rootFilePath。");
        }
    }
}
