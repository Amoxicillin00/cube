package com.cube.cloud.server.permission.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2024-06-07 16:35
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ModuleDetailsOutput extends ModuleItemOutput {

    private static final long serialVersionUID = -6756777842315092047L;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
}
