package com.cube.cloud.server.role.model;

import com.cube.cloud.core.application.model.BasePageInput;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-01-04 16:22
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RolePageInput extends BasePageInput {

    private static final long serialVersionUID = -2599453173417189997L;

    /**
     * 角色名称
     */
    @ApiModelProperty("角色名称")
    private String name;

    /**
     * 状态(0 : 禁用、1 : 启用)
     */
    @ApiModelProperty("状态(0 : 禁用、1 : 启用)")
    private Integer status;
}
