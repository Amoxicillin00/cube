package com.cube.cloud.server.user.service.impl;

import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cube.cloud.server.user.entity.UserPermission;
import com.cube.cloud.server.user.mapper.UserPermissionMapper;
import com.cube.cloud.server.user.service.UserPermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserPermissionServiceImpl extends ServiceImpl<UserPermissionMapper, UserPermission>
        implements UserPermissionService {

    private static final Logger logger = LoggerFactory.getLogger(UserPermissionServiceImpl.class);


    /**
     * 根据资源权限id删除用户相关权限
     * @param permissionId 资源权限id
     */
    @Override
    public void deleteByPermissionId(Long permissionId) {
        new LambdaUpdateChainWrapper<>(baseMapper)
                .eq(UserPermission::getPermissionId, permissionId)
                .remove();
    }
}
