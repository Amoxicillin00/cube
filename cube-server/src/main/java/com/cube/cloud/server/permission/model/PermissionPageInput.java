package com.cube.cloud.server.permission.model;

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
     * 资源模块id
     */
    @ApiModelProperty("资源模块id")
    @NotNull(message = "资源模块不能为空")
    private Long moduleId;
}
