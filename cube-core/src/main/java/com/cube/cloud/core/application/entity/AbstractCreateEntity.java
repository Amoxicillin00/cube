package com.cube.cloud.core.application.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * 创建抽象
 * @author Long
 * @date 2023-01-10 16:33
 */
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class AbstractCreateEntity<T extends Serializable> extends AbstractEntity<T>{

    private static final long serialVersionUID = 7982485338008547574L;

    /**
     * 创建人id
     */
    @TableField(value = "created_id", fill = FieldFill.INSERT)
    private Long createdId;

    /**
     * 创建时间
     */
    @TableField(value = "created_time", fill = FieldFill.INSERT)
    private LocalDateTime createdTime;
}
