package com.cube.cloud.core.web.handlers;

import com.cube.cloud.core.web.response.BaseResponse;
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
import springfox.documentation.swagger.web.ApiResourceController;
import springfox.documentation.swagger2.web.Swagger2ControllerWebMvc;

/**
 * Created with IntelliJ IDEA.
 * 统一响应结果处理
 * @author Long
 * @date 2023-04-13 14:44
 */
@Slf4j
@RestControllerAdvice(annotations = {RestController.class, RestControllerAdvice.class})
@ConditionalOnProperty("cube.response.enable")
public class ResponseBodyHandler implements ResponseBodyAdvice<Object> {


    @Override
    public boolean supports(MethodParameter returnType,
                            Class<? extends HttpMessageConverter<?>> converterType) {
        // 开启统一响应结果封装
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {

        // 返回结果
        if (body instanceof BaseResponse) {
            return body;
        }
        // 返回 swagger-resources
        else if (returnType.getDeclaringClass().getName().equals(ApiResourceController.class.getName())
         || returnType.getDeclaringClass().getName().equals(Swagger2ControllerWebMvc.class.getName())) {
            return body;
        }
        // 返回纯文本
        else if (MediaType.TEXT_HTML.equals(selectedContentType)) {
            return body;
        }
        // 返回下载流
        else if (body instanceof byte[]) {
            return body;
        }
        return BaseResponse.success(body);
    }
}
