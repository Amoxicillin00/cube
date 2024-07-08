package com.cube.cloud.server.permission.model;

import com.cube.cloud.core.application.model.AbstractModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ModuleIsAuthorizeInput extends AbstractModel<Long> {

    private static final long serialVersionUID = 2920558905930137739L;

    /**
     * 是否需要授权(false : 不需要、true : 需要)
     */
    @ApiModelProperty("是否需要授权(false : 不需要、true : 需要)")
    private boolean isAuthorize;
}
