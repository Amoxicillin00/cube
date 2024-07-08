package com.cube.cloud.server.permission.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cube.cloud.core.application.entity.AbstractModifyEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created with IntelliJ IDEA.
 * 模块管理
 * @author Long
 * @date 2024-06-07 14:30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_module")
public class Module extends AbstractModifyEntity<Long> {

    private static final long serialVersionUID = 4013550342621903644L;


    /**
     * 权限名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 路由标识
     */
    @TableField(value = "route")
    private String route;

    /**
     * 是否需要授权(0 : 不需要、1 : 需要)
     */
    @TableField(value = "is_authorize")
    private boolean isAuthorize;

    /**
     * 模块类型(0 : Web、1 : APP、2 : H5)
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 资源模块状态(0 : 禁用、1 : 启用)
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 排序
     */
    @TableField(value = "sort")
    private Integer sort;

    /**
     * 图标
     */
    @TableField(value = "icon")
    private String icon;
}
