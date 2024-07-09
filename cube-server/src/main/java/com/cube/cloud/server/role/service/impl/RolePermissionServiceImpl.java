package com.cube.cloud.server.role.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cube.cloud.core.constants.Constants;
import com.cube.cloud.server.role.entity.RolePermission;
import com.cube.cloud.server.role.mapper.RolePermissionMapper;
import com.cube.cloud.server.role.service.RolePermissionService;
import com.cube.cloud.server.user.entity.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * 角色权限
 * @author Long
 * @date 2023-05-26 15:49
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission>
        implements RolePermissionService {




    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByRoleId(Long roleId) {
        new LambdaUpdateChainWrapper<>(baseMapper)
                .eq(RolePermission::getRoleId, roleId)
                .remove();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addBatch(List<RolePermission> permissionList) {
        this.saveBatch(permissionList);
    }

    @Override
    public List<String> getListByRoleId(Long roleId) {
        return this.baseMapper.getPermissionListByRoleId(roleId);
    }


    /**
     * 超级管理员授权
     * @param permissionId 权限id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addAdminPermission(Long permissionId) {
        RolePermission rolePermission = new RolePermission();
        rolePermission.setRoleId(Constants.SUPER_ROLE);
        rolePermission.setPermissionId(permissionId);
        this.save(rolePermission);
    }

    /**
     * 根据资源权限id删除角色相关权限
     * @param permissionId 资源权限id
     */
    @Override
    public void deleteByPermissionId(Long permissionId) {
        new LambdaUpdateChainWrapper<>(baseMapper)
                .eq(RolePermission::getPermissionId, permissionId)
                .remove();
    }

}
