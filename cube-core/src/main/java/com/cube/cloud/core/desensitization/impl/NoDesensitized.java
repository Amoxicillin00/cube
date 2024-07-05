package com.cube.cloud.core.desensitization.impl;

import com.cube.cloud.core.desensitization.AbstractDesensitized;

/**
 * Created with IntelliJ IDEA.
 * 不处理脱敏
 * @author Long
 * @date 2023-08-22 17:35
 */
public class NoDesensitized extends AbstractDesensitized {

    /**
     * 不处理实例
     */
    public static final NoDesensitized INSTANCE = new NoDesensitized();


    @Override
    public String apply(String value) {
        return value;
    }
}
