package com.cube.cloud.core.desensitization.impl;

import com.cube.cloud.core.desensitization.AbstractDesensitized;
import org.apache.commons.lang3.StringUtils;

/**
 * Created with IntelliJ IDEA.
 * 银行卡号脱敏
 * @author Long
 * @date 2023-08-22 17:05
 */
public class BankCardDesensitized extends AbstractDesensitized {

    /**
     * 银行卡号实例
     */
    public static final BankCardDesensitized INSTANCE = new BankCardDesensitized();


    @Override
    public String apply(String value) {
        if (StringUtils.isBlank(value)) {
            return "";
        }

        value = value.trim();
        if (value.length() >= 10) {
            return getValueLeft(value, 6) +
                    getSymbol('*', value.length() - 10) +
                    getValueRight(value, 4);
        }

        return this.desensitizedHandler(value);
    }
}
