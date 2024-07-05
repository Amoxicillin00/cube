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
@TableName("sys_permission")
public class Permission implements Serializable {

    private static final long serialVersionUID = 4453962235211253608L;


    /**
     * 唯一id
     */
    @Id
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 资源id
     */
    @TableField(value = "resource_id")
    private Long resourceId;

    /**
     * 权限路径path
     */
    @TableField(value = "path")
    private String path;
}
