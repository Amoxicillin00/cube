package com.cube.cloud.core.log.annotation;

import com.cube.cloud.core.log.OperateType;

import java.lang.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * 日志注解
 * @author Long
 * @date 2023-08-23 16:42
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    /**
     * 模块名称
     * @return name
     */
    String name() default "";

    /**
     * 操作类型
     * @return OperateType
     */
    OperateType type() default OperateType.OTHER;
}


