package com.cube.cloud.core.desensitization.impl;

import com.cube.cloud.core.desensitization.AbstractDesensitized;
import org.apache.commons.lang3.StringUtils;

/**
 * Created with IntelliJ IDEA.
 * 手机号脱敏
 * @author Long
 * @date 2023-08-22 17:01
 */
public class MobilePhoneDesensitized extends AbstractDesensitized {

    /**
     * 手机号实例
     */
    public static final MobilePhoneDesensitized INSTANCE = new MobilePhoneDesensitized();


    @Override
    public String apply(String value) {
        if (StringUtils.isBlank(value)) {
            return "";
        }

        value = value.trim();
        if (value.length() >= 7) {
            return getValueLeft(value, 3) +
                    getSymbol('*', value.length() - 7) +
                    getValueRight(value, 4);
        }

        return this.desensitizedHandler(value);
    }
}
