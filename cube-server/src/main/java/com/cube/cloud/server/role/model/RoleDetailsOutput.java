package com.cube.cloud.server.role.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created with IntelliJ IDEA.
 * 角色用户信息
 * @author Long
 * @date 2023-01-04 16:22
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleDetailsOutput extends RoleItemOutput{

    private static final long serialVersionUID = -3146374340521141137L;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
}
