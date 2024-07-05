package com.cube.cloud.server.permission.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.cube.cloud.core.application.model.BasePageInput;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-01-04 16:20
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PermissionPageInput extends BasePageInput {

    private static final long serialVersionUID = -356935883802508825L;

    /**
     * 资源名称
     */
    @ApiModelProperty("资源名称")
    @Length(max = 50, message = "资源名称长度不能超过" + 50 + "个字。")
    private String name;

    /**
     * 路由标识
     */
    @ApiModelProperty("路由标识")
    @Length(max = 50, message = "路由标识长度不能超过" + 50 + "个字。")
    private String route;

    /**
     * 资源类型(0 : 菜单、1 : 按钮、2 : 权限)
     */
    @ApiModelProperty("资源类型(0 : 菜单、1 : 按钮、2 : 权限)")
    private Integer type;

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
