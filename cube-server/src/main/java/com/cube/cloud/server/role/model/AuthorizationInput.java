package com.cube.cloud.server.role.model;

import com.cube.cloud.core.application.model.AbstractModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * 授权
 * @author Long
 * @date 2023-05-30 15:03
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AuthorizationInput extends AbstractModel<Long> {

    private static final long serialVersionUID = -5015430693898606642L;

    /**
     * 权限集合
     */
    @ApiModelProperty("权限集合")
    @NotEmpty(message = "权限集合不能为空")
    private List<PermissionInput> permissions;
}
