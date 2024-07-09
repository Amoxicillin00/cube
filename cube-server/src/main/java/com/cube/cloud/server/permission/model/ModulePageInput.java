package com.cube.cloud.server.permission.model;

import com.cube.cloud.core.application.model.BasePageInput;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ModulePageInput extends BasePageInput {

    private static final long serialVersionUID = -356935883802508827L;

    /**
     * 资源名称
     */
    @ApiModelProperty("模块名称")
    private String name;

    /**
     * 唯一路由标识
     */
    @ApiModelProperty("唯一路由标识")
    private String route;

    /**
     * 模块类型(0 : Web、1 : APP、2 : H5)
     */
    @ApiModelProperty("模块类型(0 : Web、1 : APP、2 : H5)")
    private Integer type;
}
