package com.cube.cloud.server.role.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * 角色权限
 * @author Long
 * @date 2023-05-22 14:12
 */
@Data
@TableName("sys_role_permission")
public class RolePermission implements Serializable {

    private static final long serialVersionUID = 6909536657328692122L;

    /**
     * 角色权限主键id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 角色id
     */
    @TableField(value = "role_id")
    private Long roleId;

    /**
     * 权限id
     */
    @TableField(value = "permission_id")
    private Long permissionId;

}
