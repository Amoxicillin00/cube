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
 * 修改抽象
 * @author Long
 * @date 2023-01-10 16:36
 */
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class AbstractModifyEntity<T extends Serializable> extends AbstractCreateEntity<T> {

    private static final long serialVersionUID = -7685790569625478847L;

    /**
     * 修改人
     */
    @TableField(value = "modified_id", fill = FieldFill.INSERT_UPDATE)
    private Long modifiedId;

    /**
     * 修改时间
     */
    @TableField(value = "modified_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime modifiedTime;
}
