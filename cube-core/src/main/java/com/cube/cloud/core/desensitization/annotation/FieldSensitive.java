package com.cube.cloud.core.desensitization.annotation;

import com.cube.cloud.core.desensitization.SensitiveType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created with IntelliJ IDEA.
 * 脱敏注解
 * @author Long
 * @date 2023-08-22 15:33
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldSensitive {

    SensitiveType value();
}
