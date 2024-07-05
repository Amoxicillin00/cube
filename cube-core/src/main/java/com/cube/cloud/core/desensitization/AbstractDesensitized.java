package com.cube.cloud.core.desensitization;

import org.apache.commons.lang3.StringUtils;

/**
 * Created with IntelliJ IDEA.
 * 脱敏处理(目前只支持String类型字段)
 * @author Long
 * @date 2023-08-22 15:27
 */
public abstract class AbstractDesensitized implements Desensitized {

    /**
     * 脱敏处理
     * @param value 值
     * @return 脱敏值
     */
    protected String desensitizedHandler(String value) {
        if (StringUtils.isBlank(value)) {
            return "";
        }
        int length = value.length();
        int left;
        int right;
        switch (length) {
            case 1:
                left = 0;
                right = 0;
                break;
            case 2:
                left = 1;
                right = 0;
                break;
            case 3:
            case 4:
                left = 1;
                right = 1;
                break;
            case 5:
            case 6:
                left = 2;
                right = 1;
                break;
            default:
                left = 3;
                right = 2;
                break;
        }

        StringBuilder builder = new StringBuilder(length);
        if (left > 0) {
            builder.append(getValueLeft(value, left));
        }
        builder.append(getSymbol('*', length - left - right));
        if (right > 0) {
            builder.append(getValueRight(value, right));
        }
        return builder.toString();
    }

    /**
     * 获取符号
     * @param symbol 符合
     * @param count 个数
     * @return 符合个数
     */
    protected static String getSymbol(char symbol, int count) {
        if (count <= 0) {
            return "";
        }

        StringBuilder builder = new StringBuilder(count);
        for (int i = 1; i <= count; i++) {
            builder.append(symbol);
        }

        return builder.toString();
    }

    /**
     * 获取左边获取字符长度
     * @param value 值
     * @param length 长度
     * @return 左边获取字符长度
     */
    public static String getValueLeft(String value, int length) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        if (value.length() <= length) {
            return value;
        }
        return value.substring(0, length);
    }

    /**
     * 获取右边获取字符长度
     * @param value 值
     * @param length 长度
     * @return 右边获取字符长度
     */
    public static String getValueRight(String value, int length) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        if (value.length() <= length) {
            return value;
        }
        return value.substring(value.length() - length);
    }
}
