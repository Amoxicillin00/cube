package com.cube.cloud.server.permission.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cube.cloud.core.application.entity.AbstractModifyEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created with IntelliJ IDEA.
 * 模块资源管理
 * @author Long
 * @date 2024-06-07 14:30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_resource")
public class Resource extends AbstractModifyEntity<Long> {

    private static final long serialVersionUID = 4447094803133868344L;


    /**
     * 模块id
     */
    @TableField(value = "module_id")
    private Long moduleId;

    /**
     * 资源名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 资源路由标识
     */
    @TableField(value = "resource_route")
    private String resourceRoute;

    /**
     * 排序
     */
    @TableField(value = "sort")
    private Integer sort;
}
