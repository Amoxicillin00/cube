package com.cube.cloud.core.desensitization.impl;

import com.cube.cloud.core.desensitization.AbstractDesensitized;

/**
 * Created with IntelliJ IDEA.
 * 默认脱敏
 * @author Long
 * @date 2023-08-22 15:38
 */
public class DefaultDesensitized extends AbstractDesensitized {

    /**
     * 默认实例
     */
    public static final DefaultDesensitized INSTANCE = new DefaultDesensitized();


    @Override
    public String apply(String value) {
        return this.desensitizedHandler(value);
    }
}
