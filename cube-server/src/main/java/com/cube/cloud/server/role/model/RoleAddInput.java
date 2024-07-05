package com.cube.cloud.server.role.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-01-04 11:02
 */
@Data
public class RoleAddInput implements Serializable {

    private static final long serialVersionUID = -5013051462192299131L;

    /**
     * 所属组织id
     */
    @ApiModelProperty("所属组织id")
    @NotNull(message = "所属组织不能为空")
    private Long organizationId;

    /**
     * 角色名称
     */
    @ApiModelProperty("角色名称")
    @NotEmpty(message = "角色名称不能为空")
    @Length(max = 20, message = "角色名称长度不能超过" + 20 + "个字。")
    private String name;

    /**
     * 数据权限(0 : 全部数据、1 : 自定义数据)
     */
    @ApiModelProperty("数据权限(0 : 全部数据、1 : 自定义数据)")
    private Integer dataScope;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    @Length(max = 200, message = "备注长度不能超过200个字。")
    private String remark;
}
