package com.cube.cloud.server.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cube.cloud.server.user.entity.UserPermission;

public interface UserPermissionService extends IService<UserPermission> {

    /**
     * 根据资源权限id删除用户相关权限
     * @param permissionId 资源权限id
     */
    void deleteByPermissionId(Long permissionId);
}
