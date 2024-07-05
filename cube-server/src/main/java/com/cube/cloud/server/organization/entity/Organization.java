package com.cube.cloud.server.organization.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cube.cloud.core.application.entity.AbstractEntity;
import com.cube.cloud.core.application.entity.AbstractModifyEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-01-04 11:21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_organization")
public class Organization extends AbstractModifyEntity<Long> {

    private static final long serialVersionUID = -1862271275391488177L;

    /**
     * 组织名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 组织类型
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 组织状态(0 : 禁用、1 : 启用)
     */
    @TableField(value = "status")
    private Integer status;

}
