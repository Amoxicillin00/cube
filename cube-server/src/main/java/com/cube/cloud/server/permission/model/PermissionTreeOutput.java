package com.cube.cloud.server.permission.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.cube.cloud.core.application.model.BaseTreeOutput;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-06-27 14:47
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PermissionTreeOutput extends BaseTreeOutput<Long, PermissionTreeOutput> {

    private static final long serialVersionUID = 5984980761952637051L;

    /**
     * 资源名称
     */
    @ApiModelProperty("资源名称")
    private String name;

    /**
     * 路由标识
     */
    @ApiModelProperty("路由标识")
    private String route;

    /**
     * 资源类型(0 : 菜单、1 : 按钮、2 : 权限)
     */
    @ApiModelProperty("资源类型(0 : 菜单、1 : 按钮、2 : 权限)")
    private Integer type;

    /**
     * 权限路径
     */
    @ApiModelProperty("权限路径")
    private String path;

    /**
     * 顺序
     */
    @ApiModelProperty("顺序")
    private Integer sort;

    /**
     * 父级id
     */
    @ApiModelProperty("父级id")
    private Long parentId;

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
}
