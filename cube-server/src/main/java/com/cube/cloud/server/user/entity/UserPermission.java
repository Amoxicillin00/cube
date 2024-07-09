package com.cube.cloud.server.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("sys_user_permission")
public class UserPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 关联id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 资源权限id
     */
    @TableField(value = "permission_id")
    private Long permissionId;
}
