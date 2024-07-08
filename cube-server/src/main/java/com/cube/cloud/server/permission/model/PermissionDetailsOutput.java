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
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

}

