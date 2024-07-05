package com.cube.cloud.core.desensitization;

/**
 * Created with IntelliJ IDEA.
 * 脱敏接口
 * @author Long
 * @date 2023-08-22 15:25
 */
public interface Desensitized {

    /**
     * 脱敏运用
     * @param value 值
     * @return 脱敏值
     */
    String apply(String value);
}
