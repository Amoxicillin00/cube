package com.cube.cloud.core.web.configure;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.support.config.FastJsonConfig;
import com.alibaba.fastjson2.support.spring.http.converter.FastJsonHttpMessageConverter;
import com.cube.cloud.core.filter.ExcludePathPatterns;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * 基础配置
 * @author Long
 * @date 2023-06-05 11:22
 */
@Configuration
public class BaseMvcConfigurer implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册Sa-Token拦截器，定义详细认证规则
        registry.addInterceptor(new SaInterceptor(handler -> {
            // 校验登录match规则
            SaRouter.match("/apis/**", r -> StpUtil.checkLogin());
            // 校验权限match规则
            SaRouter.match("/**", r -> StpUtil.checkPermission(SaHolder.getRequest().getRequestPath()));
        })).addPathPatterns("/**").excludePathPatterns(ExcludePathPatterns.excludePathPatterns());
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 指定knife4j资源路径
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    }


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // fastJson转换器
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        // 配置fastJson
        FastJsonConfig config = new FastJsonConfig();
        config.setDateFormat("yyyy-MM-dd HH:mm:ss");
        config.setCharset(StandardCharsets.UTF_8);
        config.setWriterFeatures(
                JSONWriter.Feature.WriteNullListAsEmpty,
                //json格式化
                JSONWriter.Feature.PrettyFormat,
                //输出map中value为null的数据
                JSONWriter.Feature.WriteMapNullValue,
                //输出boolean 为 false
                JSONWriter.Feature.WriteNullBooleanAsFalse,
                //输出list 为 []
                JSONWriter.Feature.WriteNullListAsEmpty,
                //输出number 为 0
                JSONWriter.Feature.WriteNullNumberAsZero,
                //输出字符串 为 ""
                JSONWriter.Feature.WriteNullStringAsEmpty,
                //对map进行排序
                JSONWriter.Feature.MapSortField
        );
        converter.setFastJsonConfig(config);

        // 支持的媒体类型
        List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON);
        supportedMediaTypes.add(MediaType.APPLICATION_ATOM_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
        supportedMediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
        supportedMediaTypes.add(MediaType.APPLICATION_PDF);
        supportedMediaTypes.add(MediaType.APPLICATION_RSS_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_XHTML_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_XML);
        supportedMediaTypes.add(MediaType.IMAGE_GIF);
        supportedMediaTypes.add(MediaType.IMAGE_JPEG);
        supportedMediaTypes.add(MediaType.IMAGE_PNG);
        supportedMediaTypes.add(MediaType.TEXT_EVENT_STREAM);
        supportedMediaTypes.add(MediaType.TEXT_HTML);
        supportedMediaTypes.add(MediaType.TEXT_MARKDOWN);
        supportedMediaTypes.add(MediaType.TEXT_PLAIN);
        supportedMediaTypes.add(MediaType.TEXT_XML);
        converter.setSupportedMediaTypes(supportedMediaTypes);

        // 添加fastJson转换器
        converters.add(0, converter);
    }
}
