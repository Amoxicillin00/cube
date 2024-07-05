package com.cube.cloud.server.role.model;

import com.cube.cloud.core.application.model.AbstractModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-05-30 15:08
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PermissionInput extends AbstractModel<Long> {

    private static final long serialVersionUID = -8652197595323951274L;

}
