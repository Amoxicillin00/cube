package com.cube.cloud.server.role.query;

import com.cube.cloud.server.role.entity.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-05-30 16:43
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleQuery extends Role {

    private static final long serialVersionUID = -2099475906209290922L;

    /**
     * 组织名称
     */
    private String organizationName;

    /**
     * 创建人名称
     */
    private String createdName;

    /**
     * 修改人名称
     */
    private String modifiedName;
}
