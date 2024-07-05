package com.cube.cloud.core.function.action;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * 具有3个参数和无返回值函数
 * @author Long
 * @date 2023-08-23 11:27
 */
@FunctionalInterface
public interface FunctionThreeAction<T1, T2, T3> extends Serializable {

    /**
     * 应用
     * @param t1 参数1
     * @param t2 参数2
     * @param t3 参数3
     */
    void apply(T1 t1, T2 t2, T3 t3);
}
