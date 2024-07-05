package com.cube.cloud.core.desensitization.impl;

import com.cube.cloud.core.desensitization.AbstractDesensitized;
import org.apache.commons.lang3.StringUtils;

/**
 * Created with IntelliJ IDEA.
 * 中文姓名脱敏
 * @author Long
 * @date 2023-08-22 16:56
 */
public class ChineseNameDesensitized extends AbstractDesensitized {

    /**
     * 中文姓名实例
     */
    public static final ChineseNameDesensitized INSTANCE = new ChineseNameDesensitized();


    @Override
    public String apply(String value) {
        if (StringUtils.isBlank(value)) {
            return "";
        }

        int length = value.length();
        int left = length <= 4 ? 1 : 2;
        int right = 1;

        if (length > 2) {
            return getValueLeft(value, left) +
                    getSymbol('*', length - left - right) +
                    getValueRight(value, right);
        }

        return this.desensitizedHandler(value);
    }
}
