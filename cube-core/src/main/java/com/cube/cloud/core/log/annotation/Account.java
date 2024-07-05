package com.cube.cloud.core.log.annotation;

import java.lang.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * 账号注解
 * @author Long
 * @date 2023-08-29 16:59
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Account {
}
