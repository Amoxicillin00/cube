package com.cube.cloud.server.user.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cube.cloud.core.constants.Constants;
import com.cube.cloud.core.exception.ExceptionUtils;
import com.cube.cloud.server.role.entity.Role;
import com.cube.cloud.server.role.service.RoleService;
import com.cube.cloud.server.user.entity.UserRole;
import com.cube.cloud.server.user.mapper.UserRoleMapper;
import com.cube.cloud.server.user.service.UserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-06-07 9:35
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    private static final Logger logger = LoggerFactory.getLogger(UserRoleServiceImpl.class);




    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addBatch(Long userId, List<Long> roleIdList) {
        if (CollUtil.isEmpty(roleIdList)) {
            return;
        }
        List<UserRole> userRoleList = new ArrayList<>();
        for (Long roleId : roleIdList) {
            userRoleList.add(this.buildUserRole(userId, roleId));
        }
        this.saveBatch(userRoleList);
    }

    @Override
    public void deleteByUserId(Long userId) {
        new LambdaUpdateChainWrapper<>(baseMapper)
                .eq(UserRole::getUserId, userId)
                .remove();
    }

    @Override
    public List<UserRole> getListByUserId(Long userId) {
        // 超级管理员
        LambdaQueryWrapper<UserRole> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(UserRole::getUserId, userId);
        return this.list(wrapper);
    }

    @Override
    public List<UserRole> getListByRoleId(Long roleId) {
        LambdaQueryWrapper<UserRole> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(UserRole::getRoleId, roleId);
        return this.list(wrapper);
    }

    @Override
    public List<UserRole> getListByRoleIdList(List<Long> roleIdList) {
        LambdaQueryWrapper<UserRole> wrapper = Wrappers.lambdaQuery();
        wrapper.in(UserRole::getRoleId, roleIdList);
        return this.list(wrapper);
    }

    /**
     * 构建用户角色
     * @param userId 用户id
     * @param roleId 角色id
     * @return 用户角色信息
     */
    private UserRole buildUserRole(Long userId, Long roleId) {
        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(roleId);
        return userRole;
    }
}
