package com.cube.cloud.core.file.storage.configure;

import com.cube.cloud.core.file.storage.StorageClient;
import com.cube.cloud.core.file.storage.StorageClientContext;
import com.cube.cloud.core.file.storage.StorageClientContextImpl;
import com.cube.cloud.core.file.storage.clients.minio.MinioStorageClient;
import com.cube.cloud.core.file.storage.clients.minio.MinioStorageClientProperties;
import com.cube.cloud.core.file.storage.properties.StorageProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-01-09 15:58
 */
@Configuration
@EnableConfigurationProperties({StorageProperties.class})
public class StorageConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(StorageConfiguration.class);

    @Bean
    @ConditionalOnMissingBean(StorageClientContext.class)
    public StorageClientContext storageClientContext() {
        return new StorageClientContextImpl();
    }

    @Bean
    public BeanPostProcessor storageChannelBeanPostProcessor(StorageClientContext clientContext) {
        return new BeanPostProcessor() {
            @Override
            public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
                if (bean instanceof StorageClient) {
                    StorageClient client = (StorageClient) bean;
                    logger.info(client.toString());
                    clientContext.register(client);
                }
                return bean;
            }

            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                return bean;
            }
        };
    }

    /**
     * 本地
     * @param properties 属性
     * @return StorageClient
     */
    /*@Bean(LocalStorageClientProperties.CHANNEL_BEAN_NAME)
    @ConditionalOnProperty(name = LocalStorageClientProperties.BEAN_CONDITIONAL_PROPERTY, havingValue = "true")
    @ConditionalOnMissingBean(LocalStorageClient.class)
    public StorageClient localStorageClient(CellStorageProperties properties) {
        LocalStorageClientProperties clientProperties = properties.getLocal();
        clientProperties.initByProperties();
        return new LocalStorageClient(clientProperties);
    }*/


    /**
     * minio
     * @param properties 属性
     * @return StorageClient
     */
    @Bean(MinioStorageClientProperties.CHANNEL_BEAN_NAME)
    @ConditionalOnProperty(name = MinioStorageClientProperties.BEAN_CONDITIONAL_PROPERTY, havingValue = "true")
    @ConditionalOnMissingBean(MinioStorageClient.class)
    public StorageClient minioStorageClient(StorageProperties properties) {
        return new MinioStorageClient(properties.getMinio());
    }

}
