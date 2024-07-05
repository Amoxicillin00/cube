package com.cube.cloud.core.function.result;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * 具有5个参数和具有返回值函数
 * @author Long
 * @date 2023-08-23 14:24
 */
@FunctionalInterface
public interface FunctionFiveResult<T1, T2, T3, T4, T5, TResult> extends Serializable {

    /**
     * 应用
     * @param t1 参数1
     * @param t2 参数2
     * @param t3 参数3
     * @param t4 参数4
     * @param t5 参数5
     * @return TResult
     */
    TResult apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5);
}
