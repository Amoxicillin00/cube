package com.cube.cloud.server.role.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cube.cloud.core.constants.Constants;
import com.cube.cloud.server.role.entity.RolePermission;
import com.cube.cloud.server.role.mapper.RolePermissionMapper;
import com.cube.cloud.server.role.service.RolePermissionService;
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

    @Override
    public List<Long> getRoleIdListAndDelete(List<Long> permissionIdList) {
        LambdaQueryWrapper<RolePermission> wrapper = Wrappers.lambdaQuery();
        wrapper.in(RolePermission::getPermissionId, permissionIdList);
        List<RolePermission> rolePermissionList = this.list(wrapper);
        this.removeByIds(rolePermissionList.stream().map(RolePermission::getId).collect(Collectors.toList()));
        return rolePermissionList.stream().map(RolePermission::getRoleId).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addAdminPermission(Long permissionId) {
        RolePermission rolePermission = new RolePermission();
        rolePermission.setRoleId(Constants.SUPER_ROLE);
        rolePermission.setPermissionId(permissionId);
        this.save(rolePermission);
    }

}
