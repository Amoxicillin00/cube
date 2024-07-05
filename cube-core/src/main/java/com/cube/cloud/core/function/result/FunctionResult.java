package com.cube.cloud.core.function.result;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * 无参数和具有返回值函数
 * @author Long
 * @date 2023-08-23 14:22
 */
@FunctionalInterface
public interface FunctionResult<TResult> extends Serializable {

    /**
     * 应用
     * @return TResult
     */
    TResult apply();
}
