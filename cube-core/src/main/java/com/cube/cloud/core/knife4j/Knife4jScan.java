package com.cube.cloud.core.knife4j;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.lang.annotation.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-01-04 17:19
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
@EnableWebMvc
public @interface Knife4jScan {

    /**
     * 标题
     * @return title
     */
    String title() default "API 开发文档";

    /**
     * 扫描路径
     * @return basePackage
     */
    String basePackage() default "";
}
