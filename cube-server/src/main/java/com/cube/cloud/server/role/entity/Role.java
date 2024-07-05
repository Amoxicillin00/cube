package com.cube.cloud.server.role.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cube.cloud.core.application.entity.AbstractEntity;
import com.cube.cloud.core.application.entity.AbstractModifyEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created with IntelliJ IDEA.
 * 角色管理
 * @author Long
 * @date 2023-01-04 11:01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_role")
public class Role extends AbstractModifyEntity<Long> {

    private static final long serialVersionUID = 5130910320645922767L;

    /**
     * 所属组织id
     */
    @TableField(value = "organization_id")
    private Long organizationId;

    /**
     * 角色名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 角色状态(0 : 禁用、1 : 启用)
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 数据权限(0 : 全部数据、1 : 自定义数据)
     */
    @TableField(value = "data_scope")
    private Integer dataScope;
}
