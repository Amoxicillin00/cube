package com.cube.cloud.server.document.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-01-04 15:14
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DocumentDetailsOutput extends DocumentItemOutput{

    private static final long serialVersionUID = -2087683694146363222L;

    @ApiModelProperty(value = "备注")
    private String remark;
}
