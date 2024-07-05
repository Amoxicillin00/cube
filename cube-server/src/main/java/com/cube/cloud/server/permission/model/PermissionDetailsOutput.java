package com.cube.cloud.server.permission.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-01-04 16:19
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PermissionDetailsOutput extends PermissionItemOutput {

    private static final long serialVersionUID = 2920558905930137738L;

    /**
     * 业务类型(0 : Web、1 : APP、2 : H5)
     */
    @ApiModelProperty("业务类型(0 : Web、1 : APP、2 : H5)")
    private Integer clientType;

    /**
     * 是否需要授权(0 : 不需要、1 : 需要)
     */
    @ApiModelProperty("是否需要授权(0 : 不需要、1 : 需要)")
    private Integer isAuthorize;

    /**
     * 图标路径
     */
    @ApiModelProperty("图标路径")
    private String iconPath;

    /**
     * 父级完整名称
     */
    @ApiModelProperty("父级完整名称")
    private String parentFullName;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

}

