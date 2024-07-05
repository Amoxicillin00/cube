package com.cube.cloud.server.permission.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * 添加参数
 * @author Long
 * @date 2023-01-04 11:10
 */
@Data
public class PermissionAddInput implements Serializable {

    private static final long serialVersionUID = 6821489241838467322L;

    /**
     * 权限名称
     */
    @ApiModelProperty("资源名称")
    @NotEmpty(message = "资源名称不能为空")
    @Length(max = 50, message = "资源名称长度不能超过" + 50 + "个字。")
    private String name;

    /**
     * 路由标识
     */
    @ApiModelProperty("路由标识")
    @NotEmpty(message = "路由标识不能为空")
    @Length(max = 50, message = "路由标识长度不能超过" + 50 + "个字。")
    private String route;

    /**
     * 权限路径path
     */
    @ApiModelProperty("权限路径")
    @Length(max = 200, message = "权限路径长度不能超过" + 200 + "个字。")
    private String path;

    /**
     * 资源类型(0 : 菜单、1 : 按钮、2 : 权限)
     */
    @ApiModelProperty("资源类型(0 : 菜单、1 : 按钮、2 : 权限)")
    @NotNull(message = "资源类型不能为空")
    private Integer type;

    /**
     * 业务类型(0 : Web、1 : APP、2 : H5)
     */
    @ApiModelProperty("业务类型(0 : Web、1 : APP、2 : H5)")
    @NotNull(message = "业务类型不能为空")
    private Integer clientType;

    /**
     * 顺序
     */
    @ApiModelProperty("顺序")
    private Integer sort;

    /**
     * 是否需要授权(0 : 不需要、1 : 需要)
     */
    @ApiModelProperty("是否需要授权(0 : 不需要、1 : 需要)")
    private Integer isAuthorize;

    /**
     * 父级id
     */
    @ApiModelProperty("父级id")
    @NotNull(message = "父级id不能为空")
    private Long parentId;

    /**
     * 图标路径
     */
    @ApiModelProperty("图标路径")
    private String iconPath;
}
