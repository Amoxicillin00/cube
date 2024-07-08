package com.cube.cloud.server.permission.model;

import com.cube.cloud.core.application.model.AbstractModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class ModuleItemOutput extends AbstractModel<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 资源名称
     */
    @ApiModelProperty("模块名称")
    private String name;

    /**
     * 路由标识
     */
    @ApiModelProperty("唯一路由标识")
    private String route;

    /**
     * 是否需要授权(false : 不需要、true : 需要)
     */
    @ApiModelProperty("是否需要授权(false : 不需要、true : 需要)")
    private boolean isAuthorize;

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
     * 创建人
     */
    @ApiModelProperty("创建人")
    private String createdName;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createdTime;

    /**
     * 修改人
     */
    @ApiModelProperty("修改人")
    private String modifiedName;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date modifiedTime;
}
