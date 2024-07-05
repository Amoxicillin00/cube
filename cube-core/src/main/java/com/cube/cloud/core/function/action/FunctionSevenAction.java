package com.cube.cloud.core.function.action;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * 具有7个参数和无返回值函数
 * @author Long
 * @date 2023-08-23 14:08
 */
@FunctionalInterface
public interface FunctionSevenAction<T1, T2, T3, T4, T5, T6, T7> extends Serializable {

    /**
     * 应用
     * @param t1 参数1
     * @param t2 参数2
     * @param t3 参数3
     * @param t4 参数4
     * @param t5 参数5
     * @param t6 参数6
     * @param t7 参数7
     */
    void apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7);
}
