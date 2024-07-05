package com.cube.cloud.core.web.handlers;

import com.cube.cloud.core.application.model.BasePageOutput;
import com.cube.cloud.core.desensitization.annotation.FieldSensitive;
import com.cube.cloud.core.web.response.BaseResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 * 脱敏处理
 * @author Long
 * @date 2023-08-22 16:01
 */
@Slf4j
@RestControllerAdvice(annotations = {RestController.class, RestControllerAdvice.class})
@ConditionalOnProperty("cube.desensitized.enable")
public class DesensitizedHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType,
                            Class<? extends HttpMessageConverter<?>> converterType) {
        // 开启脱敏处理
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {

        // 字段脱敏
        desensitizedHandler(body);
        return body;
    }


    /**
     * 托敏处理
     * @param object 托敏对象
     */
    @SneakyThrows
    private void desensitizedHandler(Object object) {
        if (Objects.isNull(object)) {
            return;
        }

        if (object instanceof Collection) {
            // List递归字段处理
            ((Collection<?>) object).forEach(this::desensitizedHandler);
        } else if (object instanceof Map) {
            // Map递归字段处理
            ((Map<?, ?>) object).forEach((key, value) -> desensitizedHandler(value));
        } else if (object instanceof BasePageOutput) {
            // BasePageOutput递归字段处理
            ((BasePageOutput<?>) object).getRecords().forEach(this::desensitizedHandler);
        } else if (object instanceof BaseResponse) {
            // BaseResponse字段处理
            desensitizedHandler(((BaseResponse<?>) object).getData());
        }

        // 字段数组
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            // 判断是否有脱敏注解
            FieldSensitive fieldSensitive = field.getAnnotation(FieldSensitive.class);
            if (Objects.isNull(fieldSensitive)) {
                continue;
            }
            // 只处理String类型的字段
            if (!field.getType().equals(String.class)) {
                continue;
            }

            field.setAccessible(true);
            String value = (String) field.get(object);
            // 脱敏处理
            String desensitizedValue = fieldSensitive.value().getInstance().apply(value);
            // 替换值
            field.set(object, desensitizedValue);
        }
    }
}
