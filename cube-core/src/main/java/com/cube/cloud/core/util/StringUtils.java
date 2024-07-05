package com.cube.cloud.core.util;

import org.apache.tomcat.util.codec.binary.Base64;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * String工具
 * @author Long
 * @date 2023-01-09 12:27
 */
public class StringUtils {

    private static final char EXTENSION_SEPARATOR = '.';

    public final static String REGEX_NEWLINE = "\n|\r\n|\r";

    /**
     * 判断是否为null或空字符串
     * @param str 字符串
     * @return boolean
     */
    public static boolean isNullOrEmpty(Object str) {
        return (str == null || "".equals(str));
    }

    /**
     * 长度是否大于零
     * @param str 字符串
     * @return boolean
     */
    public static boolean hasLength(CharSequence str) {
        return (str != null && str.length() > 0);
    }

    /**
     * 长度是否大于零
     * @param str 字符串
     * @return boolean
     */
    public static boolean hasLength(String str) {
        return hasLength((CharSequence) str);
    }

    /**
     * 判断是否为非null或非空白字符串
     * @param str 字符串
     * @return boolean
     */
    public static boolean isNotNullOrBlank(CharSequence str) {
        return !isNullOrBlank(str);
    }

    /**
     * 判断是否为非null或非空白字符串
     * @param str 字符串
     * @return boolean
     */
    public static boolean isNotNullOrBlank(String str) {
        return !isNullOrBlank(str);
    }

    /**
     * 判断是否为null或空白字符串
     * @param str 字符串
     * @return boolean
     */
    public static boolean isNullOrBlank(CharSequence str) {
        if (!hasLength(str)) {
            return true;
        }
        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }


    /**
     * 判断是否为null或空白字符串
     * @param str 字符串
     * @return boolean
     */
    public static boolean isNullOrBlank(String str) {
        return isNullOrBlank((CharSequence) str);
    }

    /**
     * 是否包含空白
     * @param str 字符串
     * @return boolean
     */
    public static boolean containsWhitespace(CharSequence str) {
        if (!hasLength(str)) {
            return false;
        }
        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否包含空白
     * @param str 字符串
     * @return boolean
     */
    public static boolean containsWhitespace(String str) {
        return containsWhitespace((CharSequence) str);
    }

    private static String changeFirstCharacterCase(String str, boolean capitalize) {
        if (str == null || str.length() == 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str.length());
        if (capitalize) {
            sb.append(Character.toUpperCase(str.charAt(0)));
        } else {
            sb.append(Character.toLowerCase(str.charAt(0)));
        }
        sb.append(str.substring(1));
        return sb.toString();
    }

    /**
     * 骆锋命名(首个字母为小写)
     * @param str 字符串
     * @return String
     */
    public static String lowerCaseCapitalize(String str) {
        return changeFirstCharacterCase(str, false);
    }

    /**
     * 帕斯卡命名(首个字母为大写)
     * @param str 字符串
     * @return String
     */
    public static String upperCaseCapitalize(String str) {
        return changeFirstCharacterCase(str, true);
    }

    /**
     * 替换
     * @param inString 字符串
     * @param oldPattern 旧字符
     * @param newPattern 新字符
     * @return String
     */
    public static String replace(String inString, String oldPattern, String newPattern) {
        if (!hasLength(inString) || !hasLength(oldPattern) || newPattern == null) {
            return inString;
        }
        StringBuilder sb = new StringBuilder();
        int pos = 0;
        int index = inString.indexOf(oldPattern);
        int patLen = oldPattern.length();
        while (index >= 0) {
            sb.append(inString.substring(pos, index));
            sb.append(newPattern);
            pos = index + patLen;
            index = inString.indexOf(oldPattern, pos);
        }
        sb.append(inString.substring(pos));
        return sb.toString();
    }

    /**
     * 获左边获取字符长度
     * @param value 字符串
     * @param length 长度
     * @return String
     */
    public static String getLeft(String value, int length) {
        if (value == null) {
            return null;
        }
        if (value.length() <= length) {
            return value;
        }
        return value.substring(0, length);
    }

    /**
     * 获右边获取字符长度
     * @param value 字符串
     * @param length 长度
     * @return String
     */
    public static String getRight(String value, int length) {
        if (value == null) {
            return null;
        }
        if (value.length() <= length) {
            return value;
        }
        return value.substring(value.length() - length, value.length());
    }

    /**
     * 枚举转换为数组
     * @param enumeration 枚举
     * @return String[]
     */
    public static String[] toArray(Enumeration<String> enumeration) {
        if (enumeration == null) {
            return null;
        }
        List<String> list = Collections.list(enumeration);
        return list.toArray(new String[list.size()]);
    }

    /**
     * 连接字符窜
     * @param arr 数据
     * @param separator 分隔符
     * @return String
     */
    public static String join(String[] arr, String separator) {
        if (arr == null || arr.length == 0) {
            return "";
        }
        if (arr.length == 1) {
            return arr[0];
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (i > 0) {
                sb.append(separator);
            }
            sb.append(arr[i]);
        }
        return sb.toString();
    }

    /**
     * 移除所有空格、换行符、Tab键
     * @param str 字符串
     * @return String
     */
    public static String removeALLWhitespace(String str) {
        if (str == null) {
            return null;
        }
        str = str.trim();
        if (str.length() == 0) {
            return str;
        }
        char[] chars = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char ch : chars) {
            if (!Character.isWhitespace(ch)) {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    /**
     *  移除开始
     * @param str 字符串
     * @param removeChar 移除字符
     * @return String
     */
    public static String removeStart(String str, char removeChar) {
        if (str == null) {
            return null;
        }
        if (str.length() == 0) {
            return str;
        }
        String value = String.valueOf(removeChar);
        while (str.length() > 0 && str.startsWith(value)) {
            str = str.substring(value.length());
        }
        return str;
    }

    /**
     * 移除结束
     * @param str 字符串
     * @param removeChar 移除字符
     * @return
     */
    public static String removeEnd(String str, char removeChar) {
        if (str == null) {
            return null;
        }
        if (str.length() == 0) {
            return str;
        }
        String value = String.valueOf(removeChar);
        while (str.length() > 0 && str.endsWith(value)) {
            str = str.substring(0, str.length() - value.length());
        }
        return str;
    }

    /**
     * 获取字二进制
     * @param str 字符串
     * @param charset 编码
     * @return byte[]
     */
    public static byte[] getBytes(final String str, final Charset charset) {
        if (str == null) {
            return null;
        }
        return str.getBytes(charset);
    }

    /**
     * 获取按字符 utf8 字节编码
     * @param str 字符串
     * @return byte[]
     */
    public static byte[] getBytesUtf8(final String str) {
        return getBytes(str, StandardCharsets.UTF_8);
    }

    /**
     * 获取二进制字符窜
     * @param bytes 字节数组
     * @param charset 编码
     * @return String
     */
    public static String getString(final byte[] bytes, final Charset charset) {
        return bytes == null ? null : new String(bytes, charset);
    }

    /**
     * 获取二进制的utf8编码字符窜
     * @param bytes 字节数组
     * @return String
     */
    public static String getStringUtf8(final byte[] bytes) {
        return getString(bytes, StandardCharsets.UTF_8);
    }

    /**
     * 是否是 base64 位编码
     * @param base64 至
     * @param charset 编码
     * @return boolean
     */
    public static boolean isBase64(final String base64, final Charset charset) {
        return Base64.isBase64(StringUtils.getBytes(base64, charset));
    }

    /**
     * 是否是utf-8 的 base64 位编码
     * @param base64 值
     * @return boolean
     */
    public static boolean isBase64Utf8(final String base64) {
        return Base64.isBase64(StringUtils.getBytes(base64, StandardCharsets.UTF_8));
    }

    /**
     * 字符窜编码为Base64字节数组
     * @param str 字符串
     * @param charset 编码
     * @return byte[]
     */
    public static byte[] encodeBase64(final String str, final Charset charset) {
        return Base64.encodeBase64(StringUtils.getBytes(str, charset));
    }

    /**
     * 字符窜编码为Base64字符串
     * @param str 字符串
     * @param charset 编码
     * @return String
     */
    public static String encodeBase64String(final String str, final Charset charset) {
        return getString(encodeBase64(str, charset), charset);
    }

    /**
     * 采用utf8将字符窜编码为Base64二进制
     * @param str 字符串
     * @return byte[]
     */
    public static byte[] encodeBase64Utf8(final String str) {
        return encodeBase64(str, StandardCharsets.UTF_8);
    }

    /**
     * 采用Utf8字符窜编码为Base64字符串
     * @param str 值
     * @return String
     */
    public static String encodeBase64Utf8String(final String str) {
        return getString(encodeBase64Utf8(str), StandardCharsets.UTF_8);
    }

    /**
     * 将base64 解码为字节数组
     * @param base64 值
     * @param charset 编码
     * @return byte[]
     */
    public static byte[] decodeBase64(final String base64, final Charset charset) {
        return Base64.decodeBase64(StringUtils.getBytes(base64, charset));
    }

    /**
     * 将base64 解码为字符窜
     * @param base64 值
     * @param charset 编码
     * @return String
     */
    public static String decodeBase64String(final String base64, final Charset charset) {
        return getString(decodeBase64(base64, charset), charset);
    }

    /**
     * 使用utf8将base64 解码为字节数组
     * @param base64 值
     * @return  byte[]
     */
    public static byte[] decodeBase64Utf8(final String base64) {
        return decodeBase64(base64, StandardCharsets.UTF_8);
    }

    /**
     * 采用Utf8将base64 解码为字符窜
     * @param base64 值
     * @return String
     */
    public static String decodeBase64Utf8String(final String base64) {
        return getString(decodeBase64Utf8(base64), StandardCharsets.UTF_8);
    }

    /**
     * 倒序
     * @param code 字符串
     * @return String
     */
    public static String reverseOrder(final String code) {
        if (code == null || "".equals(code)) {
            return code;
        }
        char[] charArray = code.toCharArray();
        String result = "";
        for (int i = charArray.length - 1; i >= 0; i--) {
            result += charArray[i];
        }
        return result;
    }

    /**
     * 换行
     * @param value 值
     * @return String[]
     */
    public static String[] splitNewLine(String value) {
        if (value == null) {
            return null;
        }
        return value.split(REGEX_NEWLINE);
    }

    /**
     * 分割
     * @param regex 正则表达式
     * @param source 源字符串
     * @param isRemoveEmpty 是否移除空项
     * @return String[]
     */
    public static String[] split(String regex, String source, boolean isRemoveEmpty) {
        String[] tmp = source.split(regex);
        if (!isRemoveEmpty) {
            return tmp;
        }
        List<String> res = new ArrayList<>();
        for (String s : tmp) {
            if (!StringUtils.isNullOrBlank(s)) {
                res.add(s.trim());
            }
        }
        return res.toArray(new String[res.size()]);
    }

    /**
     * 分割，移除空项
     * @param regex 正则表达式
     * @param source 源字符串
     * @return String[]
     */
    public static String[] split(String regex, String source) {
        return split(regex, source, true);
    }

    /**
     *
     * @param str 字符串
     * @param start 开始
     * @param end 结束
     * @return 结果
     */
    public static String substring(final String str, int start, int end) {
        if (str == null) {
            return "";
        }
        if (end < 0) {
            end = str.length() + end;
        }
        if (start < 0) {
            start = str.length() + start;
        }
        if (end > str.length()) {
            end = str.length();
        }
        if (start > end) {
            return "";
        }
        if (start < 0) {
            start = 0;
        }
        if (end < 0) {
            end = 0;
        }
        return str.substring(start, end);
    }
}
