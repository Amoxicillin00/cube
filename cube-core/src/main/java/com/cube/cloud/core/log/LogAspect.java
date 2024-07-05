package com.cube.cloud.core.log;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.alibaba.fastjson.JSON;
import com.cube.cloud.core.enums.StatusEnum;
import com.cube.cloud.core.exception.BaseException;
import com.cube.cloud.core.log.annotation.Log;
import com.cube.cloud.core.log.service.LogService;
import com.cube.cloud.core.util.StringUtils;
import com.cube.cloud.core.web.context.BaseContext;
import com.cube.cloud.core.web.response.BaseResponse;
import com.cube.cloud.core.log.entity.OperationLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.NamedThreadLocal;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 * 日志Aop
 * @author Long
 * @date 2023-08-24 15:57
 */
@Slf4j
@Aspect
@Component
public class LogAspect {

    private final ExecutorService executorService = Executors.newFixedThreadPool(20, new CustomizableThreadFactory("log-"));

    @Resource
    private LogService logService;

    /**
     * 计算操作消耗时间
     */
    private static final ThreadLocal<Long> TIME_THREAD_LOCAL = new NamedThreadLocal<>("Cost Time");


    @Pointcut("@annotation(com.cube.cloud.core.log.annotation.Log)")
    public void logPointCut() {
    }

    /**
     * 处理请求前执行
     */
    @Before(value = "logPointCut()")
    public void doBefore() {
        // 操作开始前时间
        TIME_THREAD_LOCAL.set(System.currentTimeMillis());
    }

    /**
     * 处理完请求后执行
     * @param point 切点
     * @param result 放回结果
     */
    @AfterReturning(pointcut = "logPointCut()", returning = "result")
    public void doAfterReturning(JoinPoint point, Object result) {
        doHandleLog(point, null, result);
    }

    /**
     * 拦截异常操作
     * @param point 切点
     * @param exception 异常信息
     */
    @AfterThrowing(value = "logPointCut()", throwing = "exception")
    public void doAfterThrowing(JoinPoint point, BaseException exception) {
        doHandleLog(point, exception, null);
    }

    /**
     * 日志数据处理
     * @param point 切点
     * @param exception 异常信息
     * @param result 放回结果
     */
    private void doHandleLog(final JoinPoint point, final BaseException exception, Object result) {
        // 操作日志详细
        OperationLog operationLog = new OperationLog();
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Log annotation = method.getDeclaredAnnotation(Log.class);
        // 操作名称
        operationLog.setOperationName(annotation.name());
        // 操作类型
        operationLog.setOperationType(annotation.type().getCode());

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (Objects.nonNull(requestAttributes)) {
            HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
            // 请求方式
            operationLog.setRequestMethod(request.getMethod());
            // 请求ip
            operationLog.setRequestIp(getIp(request));
            // 请求 url
            operationLog.setRequestUrl(request.getRequestURI());
            // 方法名
            operationLog.setMethod(point.getTarget().getClass().getName() + "." + point.getSignature().getName() + "()");

            UserAgent userAgent = UserAgentUtil.parse(request.getHeader("User-Agent"));
            // 浏览器名称
            operationLog.setOperatorBrowser(userAgent.getBrowser().getName());
            // 操作系统名称
            operationLog.setOperatorOs(userAgent.getOs().getName());

            // 请求参数
            Map<String, String[]> parameterMap = request.getParameterMap();
            if (CollUtil.isEmpty(parameterMap) && (HttpMethod.PUT.name().equals(request.getMethod()) || HttpMethod.POST.name().equals(request.getMethod()))) {
                if (point.getArgs().length > 0 && Objects.nonNull(point.getArgs()[0])) {
                    operationLog.setRequestParam(StringUtils.substring(JSON.toJSONString(point.getArgs()[0]), 0, 2000));
                }
            } else {
                HashMap<String, String[]> params = new HashMap<>(parameterMap);
                operationLog.setRequestParam(StringUtils.substring(JSON.toJSONString(params), 0, 2000));
            }
        }

        if (Objects.isNull(exception)) {
            // 操作状态(成功)
            operationLog.setStatus(StatusEnum.ENABLE.getCode());
            operationLog.setMessage(annotation.name() + "成功");

            // 用户信息
            operationLog.setUserId(BaseContext.getCurrentUser().getId());
            operationLog.setUserName(BaseContext.getCurrentUser().getName());

            // 响应数据
            Object object = BaseResponse.success(result);
            operationLog.setResponseResult(JSON.toJSONString(object));

        } else {
            // 操作状态(失败)
            operationLog.setStatus(StatusEnum.DISABLE.getCode());
            operationLog.setMessage(exception.getMessage());

            Object object = BaseResponse.error(exception.getCode(), exception.getMessage());
            operationLog.setResponseResult(JSON.toJSONString(object));
        }

        operationLog.setCostTime(System.currentTimeMillis() - TIME_THREAD_LOCAL.get());
        TIME_THREAD_LOCAL.remove();
        // 异步任务写入日志
        executorService.submit(()-> execSaveLogTask(operationLog));
    }

    /**
     * 保存日志信息
     * @param operationLog 日志信息
     */
    private void execSaveLogTask(final OperationLog operationLog) {
        // 写入时间
        operationLog.setCreatedTime(LocalDateTime.now());
        // 保存日志信息
        logService.save(operationLog);
    }

    /**
     * 获取ip地址
     * @return ip地址
     */
    private String getIp(HttpServletRequest request){
        String ip = request.getHeader("X-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
