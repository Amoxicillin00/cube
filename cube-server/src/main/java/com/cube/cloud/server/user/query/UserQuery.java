package com.cube.cloud.server.user.query;

import com.cube.cloud.server.user.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-06-07 10:53
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserQuery extends User {

    private static final long serialVersionUID = -8362494629170377982L;

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
