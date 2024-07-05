package com.cube.cloud.core.application.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-01-04 14:59
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseIdInput extends AbstractModel<Long> {

    private static final long serialVersionUID = 3565238491801227346L;

}
