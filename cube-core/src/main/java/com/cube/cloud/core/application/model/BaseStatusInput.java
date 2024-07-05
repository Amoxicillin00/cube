package com.cube.cloud.core.application.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 * 状态
 * @author Long
 * @date 2023-06-15 17:32
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseStatusInput extends AbstractModel<Long>{

    private static final long serialVersionUID = 5323759445839373055L;

    /**
     * 状态(0 : 禁用、1 : 启用)
     */
    @ApiModelProperty("状态(0 : 禁用、1 : 启用)")
    @NotNull(message = "状态枚举不能为空")
    private Integer status;
}
