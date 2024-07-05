package com.cube.cloud.server.permission.query;

import com.cube.cloud.server.permission.entity.Permission;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-06-27 9:56
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PermissionQuery extends Permission {

    private static final long serialVersionUID = -8457002697647553763L;

    /**
     * 创建人名称
     */
    private String createdName;

    /**
     * 修改人名称
     */
    private String modifiedName;
}
