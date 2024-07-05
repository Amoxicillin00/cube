package com.cube.cloud.server.organization.model;

import com.cube.cloud.core.application.model.AbstractModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-01-04 16:16
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OrganizationModifyInput extends AbstractModel<Long> {

    private static final long serialVersionUID = -1339014763055805697L;
}
