package com.cube.cloud.core.file.storage.clients.local;

import com.cube.cloud.core.application.model.AbstractBucket;
import lombok.ToString;

/**
 * Created with IntelliJ IDEA.
 * 本地分区
 * @author Long
 * @date 2023-01-10 14:47
 */
@ToString(callSuper = true)
public class LocalBucket extends AbstractBucket {

    private static final long serialVersionUID = 8836606087498193678L;


    protected LocalBucket(String name) {
        super(name);
    }
}
