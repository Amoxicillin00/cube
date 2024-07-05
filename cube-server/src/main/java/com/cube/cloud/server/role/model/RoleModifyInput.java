package com.cube.cloud.server.role.model;

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
 * @date 2023-01-04 16:22
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleModifyInput extends AbstractModel<Long> {

    private static final long serialVersionUID = -4880756090807707296L;

    /**
     * 角色名称
     */
    @ApiModelProperty("角色名称")
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
