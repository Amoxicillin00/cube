package com.cube.cloud.core.desensitization.impl;

import com.cube.cloud.core.desensitization.AbstractDesensitized;
import org.apache.commons.lang3.StringUtils;

/**
 * Created with IntelliJ IDEA.
 * 电子邮箱地址脱敏
 * @author Long
 * @date 2023-08-22 17:02
 */
public class EmailAddressDesensitized extends AbstractDesensitized {

    /**
     * 电子邮箱地址实例
     */
    public static final EmailAddressDesensitized INSTANCE = new EmailAddressDesensitized();

    private static final String SEPARATE = "@";

    @Override
    public String apply(String value) {
        if (StringUtils.isBlank(value)) {
            return "";
        }

        if (value.contains(SEPARATE)) {
            String[] emails = value.trim().split(SEPARATE);
            return desensitizedHandler(emails[0]) +
                    SEPARATE +
                    emails[1];
        }

        return this.desensitizedHandler(value.trim());
    }
}
