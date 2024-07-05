package com.cube.cloud.core.desensitization.impl;

import com.cube.cloud.core.desensitization.AbstractDesensitized;
import org.apache.commons.lang3.StringUtils;

/**
 * Created with IntelliJ IDEA.
 * 身份证号脱敏
 * @author Long
 * @date 2023-08-22 16:58
 */
public class IdCardDesensitized extends AbstractDesensitized {

    /**
     * 身份证号实例
     */
    public static final IdCardDesensitized INSTANCE = new IdCardDesensitized();


    @Override
    public String apply(String value) {
        if (StringUtils.isBlank(value)) {
            return "";
        }

        value = value.trim();
        if (value.length() >= 8) {
            return getValueLeft(value, 6) +
                    getSymbol('*', value.length() - 8) +
                    getValueRight(value, 2);
        }

        return this.desensitizedHandler(value);
    }
}
