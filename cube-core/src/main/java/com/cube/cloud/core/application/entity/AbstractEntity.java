package com.cube.cloud.core.application.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.cube.cloud.core.log.annotation.Id;
import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * 实体抽象
 * @author Long
 * @date 2023-01-04 11:33
 */
@Data
public abstract class AbstractEntity<T extends Serializable> implements Serializable {

    private static final long serialVersionUID = -441564050589710904L;

    /**
     * 主键id
     */
    @Id
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private T id;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 是否删除（0 : 未删除, 1 : 已删除）
     */
    @TableField(value = "is_delete", fill = FieldFill.INSERT)
    @TableLogic
    private boolean isDelete;
}
