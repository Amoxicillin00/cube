package com.cube.cloud.core.file.storage;

import com.cube.cloud.core.application.channel.AbstractChannelContext;

/**
 * Created with IntelliJ IDEA.
 * 存储实现类
 * @author Long
 * @date 2023-01-09 16:22
 */
public class StorageClientContextImpl extends AbstractChannelContext<StorageClient> implements StorageClientContext{

    public StorageClientContextImpl() {
        super(16);
    }
}
