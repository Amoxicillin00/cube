package com.cube.cloud.core.knife4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * knife4j配置文件
 * @author Long
 * @date 2023-01-04 17:26
 */
@Configuration
@EnableSwagger2
public class Knife4jConfigurer {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public Docket createDocket() {
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(SpringBootApplication.class);
        String key = beansWithAnnotation.keySet().iterator().next();
        Object applicationBean = beansWithAnnotation.get(key);
        Knife4jScan annotation = applicationBean.getClass().getAnnotation(Knife4jScan.class);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInfo(annotation.title()))
                .groupName("Cube系统服务")
                .select()
                .apis(RequestHandlerSelectors.basePackage(annotation.basePackage()))
                .paths(PathSelectors.any())
                .build()
                .globalRequestParameters(buildGlobalRequestParameters());
    }

    /**
     * 构建文档摘要信息
     * @param title 标题
     * @return 文档摘要信息
     */
    private ApiInfo buildApiInfo(String title) {
        return new ApiInfoBuilder()
                .title(title)
                .description("# Cube Project For Spring Boot API Document")
                .contact(new Contact("Long", "https://doc.xiaominfo.com/", "1572553357@qq.com"))
                .termsOfServiceUrl("https://doc.xiaominfo.com/")
                .version("v1.0.0")
                .build();
    }

    /**
     * 构建全局通用参数
     * @return 全局通用参数列表
     */
    private List<RequestParameter> buildGlobalRequestParameters() {
        List<RequestParameter> parameters = new ArrayList<>();
        parameters.add(new RequestParameterBuilder()
                .name("Authorization")
                .description("token令牌")
                .required(true)
                .in(ParameterType.HEADER)
                .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
                .required(false)
                .build());
        return parameters;
    }
}
