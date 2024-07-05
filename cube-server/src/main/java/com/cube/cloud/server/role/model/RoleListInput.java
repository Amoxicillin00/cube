package com.cube.cloud.server.role.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-05-30 16:12
 */
@Data
public class RoleListInput implements Serializable {

    private static final long serialVersionUID = -9207251368974728174L;

    /**
     * 角色名称
     */
    @ApiModelProperty("角色名称")
    private String name;
}
