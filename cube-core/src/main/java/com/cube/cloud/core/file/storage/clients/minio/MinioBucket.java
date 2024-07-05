package com.cube.cloud.core.file.storage.clients.minio;

import com.cube.cloud.core.application.model.AbstractBucket;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created with IntelliJ IDEA.
 * Minio 分区
 * @author Long
 * @date 2023-01-10 14:06
 */
@ToString(callSuper = true)
@Getter
@Setter
public class MinioBucket extends AbstractBucket {

    private static final long serialVersionUID = -7071322576144347880L;

    protected MinioBucket(String name) {
        super(name);
    }
}
