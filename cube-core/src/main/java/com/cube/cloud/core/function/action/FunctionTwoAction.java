package com.cube.cloud.core.function.action;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * 具有2个参数和无返回值函数
 * @author Long
 * @date 2023-01-04 16:46
 */
@FunctionalInterface
public interface FunctionTwoAction<T1, T2> extends Serializable {

    /**
     * 应用
     * @param t1 参数1
     * @param t2 参数2
     */
    void apply(T1 t1, T2 t2);
}
