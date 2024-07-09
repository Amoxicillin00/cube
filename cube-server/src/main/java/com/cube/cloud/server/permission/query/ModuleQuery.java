package com.cube.cloud.server.permission.query;

import com.cube.cloud.server.permission.entity.Module;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ModuleQuery extends Module {

    private static final long serialVersionUID = -8457002697647553767L;


    /**
     * 创建人名称
     */
    private String createdName;

    /**
     * 修改人名称
     */
    private String modifiedName;
}
