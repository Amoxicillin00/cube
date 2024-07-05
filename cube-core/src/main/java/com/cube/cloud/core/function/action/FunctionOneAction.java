package com.cube.cloud.core.function.action;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * 具有1个参数无返回值
 * @author Long
 * @date 2023-08-23 14:11
 */
@FunctionalInterface
public interface FunctionOneAction<T> extends Serializable {

    /**
     * 应用
     * @param t 参数
     */
    void apply(T t);
}
