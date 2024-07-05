package com.cube.cloud.server.permission.model;

import com.cube.cloud.core.application.model.AbstractModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-01-04 16:19
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PermissionModifyInput extends AbstractModel<Long> {

    private static final long serialVersionUID = -2821971952054554644L;


    /**
     * 权限名称
     */
    @ApiModelProperty("权限名称")
    @NotEmpty(message = "权限名称不能为空")
    @Length(max = 50, message = "权限名称长度不能超过" + 50 + "个字。")
    private String name;

    /**
     * 权限路径path
     */
    @ApiModelProperty("权限路径path")
    @Length(max = 200, message = "权限路径path长度不能超过" + 200 + "个字。")
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
    private Long parentId;

    /**
     * 图标路径
     */
    @ApiModelProperty("图标路径")
    private String iconPath;
}
