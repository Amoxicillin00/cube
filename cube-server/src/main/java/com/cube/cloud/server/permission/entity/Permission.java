package com.cube.cloud.server.permission.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cube.cloud.core.application.entity.AbstractModifyEntity;
import com.cube.cloud.core.log.annotation.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * 资源权限管理
 * @author Long
 * @date 2023-06-07 14:30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_permission")
public class Permission extends AbstractModifyEntity<Long> {

    private static final long serialVersionUID = 4453962235211253608L;


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
    @TableField(value = "route")
    private String route;

    /**
     * 权限路径URL
     */
    @TableField(value = "url")
    private String url;

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
