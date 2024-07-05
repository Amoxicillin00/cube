package com.cube.cloud.core.log.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-08-24 15:55
 */
@Data
@TableName("sys_operation_log")
public class OperationLog implements Serializable {

    private static final long serialVersionUID = -5839373653005077123L;

    /**
     * 主键id(雪花算法)
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 操作名称
     */
    @TableField(value = "operation_name")
    private String operationName;

    /**
     * 请求方法
     */
    @TableField(value = "method")
    private String method;

    /**
     * 操作类型(0 : 登录、1 : 添加、2 : 删除、3 : 修改、4 : 查询、5 : 导入、6 : 导出、99  : 默认)
     */
    @TableField(value = "operation_type")
    private Integer operationType;

    /**
     * 请求ip
     */
    @TableField(value = "request_ip")
    private String requestIp;

    /**
     * 请求方式
     */
    @TableField(value = "request_method")
    private String requestMethod;

    /**
     * 请求url
     */
    @TableField(value = "request_url")
    private String requestUrl;

    /**
     * 操作状态(0 : 失败、1 : 成功)
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 状态消息
     */
    @TableField(value = "message")
    private String message;

    /**
     * 操作用户id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 操作用户名称
     */
    @TableField(value = "user_name")
    private String userName;


    /**
     * 请求参数
     */
    @TableField(value = "request_param")
    private String requestParam;

    /**
     * 响应数据
     */
    @TableField(value = "response_result")
    private String responseResult;

    /**
     * 消耗时间
     */
    @TableField(value = "cost_time")
    private Long costTime;

    /**
     * 创建时间
     */
    @TableField(value = "created_time", fill = FieldFill.INSERT)
    private LocalDateTime createdTime;

    /**
     * 响应数据
     */
    @TableField(value = "operator_browser")
    private String operatorBrowser;

    /**
     * 响应数据
     */
    @TableField(value = "operator_os")
    private String operatorOs;
}
