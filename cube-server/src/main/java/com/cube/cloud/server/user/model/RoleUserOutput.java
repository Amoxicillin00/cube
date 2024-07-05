package com.cube.cloud.server.user.model;

import com.cube.cloud.core.application.model.AbstractModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-05-30 16:02
 */
@Data
public class RoleUserOutput extends AbstractModel<Long> {

    private static final long serialVersionUID = 7309742806957262238L;

    /**
     * 用户名称
     */
    @ApiModelProperty(value = "用户名称")
    private String name;
}
