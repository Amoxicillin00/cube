package com.cube.cloud.core.function.result;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * 具有2个参数和具有返回值函数
 * @author Long
 * @date 2023-08-23 14:24
 */
@FunctionalInterface
public interface FunctionTwoResult<T1, T2, TResult> extends Serializable {

    /**
     * 应用
     * @param t1 参数1
     * @param t2 参数2
     * @return TResult
     */
    TResult apply(T1 t1, T2 t2);
}
