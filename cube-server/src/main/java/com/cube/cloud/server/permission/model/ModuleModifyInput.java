package com.cube.cloud.server.permission.model;

import com.cube.cloud.core.application.model.AbstractModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ModuleModifyInput extends AbstractModel<Long> {

    private static final long serialVersionUID = -2821971952054554646L;

    /**
     * 父级id
     */
    @ApiModelProperty("父级id")
    private Long parentId;

    /**
     * 模块名称
     */
    @ApiModelProperty("模块名称")
    private String name;

    /**
     * 模块类型(0 : Web、1 : APP、2 : H5)
     */
    @ApiModelProperty("模块类型(0 : Web、1 : APP、2 : H5)")
    private Integer type;

    /**
     * 排序
     */
    @ApiModelProperty("排序")
    private Integer sort;

    /**
     * 图标icon
     */
    @ApiModelProperty("图标icon")
    private String icon;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
}
