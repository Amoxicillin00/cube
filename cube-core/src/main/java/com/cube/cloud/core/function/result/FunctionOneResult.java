package com.cube.cloud.core.function.result;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * 具有1个参数和具有返回值函数
 * @author Long
 * @date 2023-08-23 14:23
 */
@FunctionalInterface
public interface FunctionOneResult<T, TResult> extends Serializable {

    /**
     * 应用
     * @param t 参数
     * @return TResult
     */
    TResult apply(T t);
}
