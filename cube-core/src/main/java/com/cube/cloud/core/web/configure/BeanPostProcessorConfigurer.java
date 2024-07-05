package com.cube.cloud.core.web.configure;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import springfox.documentation.spring.web.plugins.WebFluxRequestHandlerProvider;
import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * 由于Springfox假设Spring MVC的路径匹配策略是ANT_PATH_MATCHER，
 * 而Spring Boot 2.6.x版本的默认匹配策略是PATH_PATTERN_MATCHER，
 * 所以会导致Spring Boot 2.6.x版本引入依赖Swagger 3.0后，启动容器会报错
 * @author Long
 * @date 2023-08-22 11:07
 */
@Configuration
public class BeanPostProcessorConfigurer {

    @Bean
    public static BeanPostProcessor BeanPostProcessorHandler() {
        return new BeanPostProcessor() {
            @Override
            public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
                return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
            }

            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (bean instanceof WebMvcRequestHandlerProvider || bean instanceof WebFluxRequestHandlerProvider) {
                    definedSpringfoxHandlerMappings(getHandlerMappings(bean));
                }
                return bean;
            }

            private <T extends RequestMappingInfoHandlerMapping> void definedSpringfoxHandlerMappings(List<T> mappingList) {
                List<T> definedList = mappingList.stream()
                        .filter(mapping -> Objects.isNull(mapping.getPatternParser()))
                        .collect(Collectors.toList());
                mappingList.clear();
                mappingList.addAll(definedList);
            }

            @SuppressWarnings("unchecked")
            private List<RequestMappingInfoHandlerMapping> getHandlerMappings(Object bean) {
                try {
                    Field field = ReflectionUtils.findField(bean.getClass(), "handlerMappings");
                    field.setAccessible(true);
                    return (List<RequestMappingInfoHandlerMapping>) field.get(bean);
                } catch (IllegalArgumentException | IllegalAccessException  exception) {
                    throw new IllegalStateException(exception);
                }
            }
        };
    }
}
