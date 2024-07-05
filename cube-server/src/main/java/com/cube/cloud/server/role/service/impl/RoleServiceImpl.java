package com.cube.cloud.server.role.service.impl;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cube.cloud.core.application.dto.CurrentUser;
import com.cube.cloud.core.application.model.BaseIdInput;
import com.cube.cloud.core.application.model.BasePageOutput;
import com.cube.cloud.core.constants.Constants;
import com.cube.cloud.core.enums.DataScopeEnum;
import com.cube.cloud.core.enums.StatusEnum;
import com.cube.cloud.core.exception.ExceptionUtils;
import com.cube.cloud.core.util.MapUtils;
import com.cube.cloud.core.util.PageUtils;
import com.cube.cloud.core.web.context.BaseContext;
import com.cube.cloud.server.role.entity.Role;
import com.cube.cloud.server.role.entity.RolePermission;
import com.cube.cloud.server.role.mapper.RoleMapper;
import com.cube.cloud.server.role.model.*;
import com.cube.cloud.server.role.query.RoleQuery;
import com.cube.cloud.server.role.service.RolePermissionService;
import com.cube.cloud.server.role.service.RoleService;
import com.cube.cloud.server.user.entity.UserRole;
import com.cube.cloud.server.user.service.UserRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * 角色
 * @author Long
 * @date 2023-01-04 11:03
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private UserRoleService userRoleService;



    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(RoleAddInput input) {
        // 校验角色唯一性
        Role exist = this.getRoleByName(input.getOrganizationId(), input.getName());
        ExceptionUtils.check(Objects.nonNull(exist), "角色已存在 : " + input.getName());

        Role role = MapUtils.map(input, Role.class);
        // 状态为启用
        role.setStatus(StatusEnum.ENABLE.getCode());
        // 数据权限为全部数据
        role.setDataScope(DataScopeEnum.ALL.getCode());
        // 保存
        this.save(role);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(BaseIdInput input) {
        // 校验角色信息
        Role role = this.checkRole(input.getId());
        ExceptionUtils.check(Objects.equals(Constants.SUPER_ROLE, role.getId()), "超级管理员不可删除");
        // 用户绑定角色信息
        ExceptionUtils.check(CollUtil.isNotEmpty(this.userRoleService.getListByRoleId(role.getId())),
                "角色已绑定用户，无法删除");
        // 逻辑删除
        this.removeById(role.getId());
        // 删除角色权限数据
        this.rolePermissionService.deleteByRoleId(role.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(RoleModifyInput input) {
        // 校验角色信息
        Role role = this.checkRole(input.getId());
        BeanUtils.copyProperties(input, role);
        // 更新
        this.updateById(role);

        // 更新SaSession信息
        List<UserRole> userList = this.userRoleService.getListByRoleId(role.getId());
        if (CollUtil.isNotEmpty(userList)) {
            userList.forEach(e -> {
                CurrentUser user = StpUtil.getSessionByLoginId(e.getId()).getModel(SaSession.USER, CurrentUser.class);
                List<UserRole> userRoleList = this.userRoleService.getListByUserId(e.getUserId());
                List<Long> roleIdList = userRoleList.stream().map(UserRole::getRoleId).collect(Collectors.toList());
                List<Role> roleList = this.getListByRoleIds(roleIdList);
                if (CollUtil.isNotEmpty(roleList)) {
                    user.setRoles(roleList.stream().map(Role::getName).collect(Collectors.joining(",")));
                } else {
                    user.setRoles("");
                }
                StpUtil.getSessionByLoginId(e.getId()).set(SaSession.USER, user);
            });
        }
    }

    @Override
    public RoleDetailsOutput getDetails(BaseIdInput input) {
        // 校验角色信息
        Role role = this.checkRole(input.getId());
        return MapUtils.map(this.baseMapper.getDetails(role.getId()), RoleDetailsOutput.class);
    }

    @Override
    public List<RoleItemOutput> getList(RoleListInput input) {
        LambdaQueryWrapper<Role> wrapper = Wrappers.lambdaQuery();
        // 角色名称-模糊查询
        if (StrUtil.isNotBlank(input.getName())) {
            wrapper.like(Role::getName, input.getName());
        }

        wrapper.eq(Role::getStatus, StatusEnum.ENABLE.getCode())
                .eq(Role::getOrganizationId, BaseContext.getCurrentUser().getOrganizationId())
                .orderByDesc(Role::getCreatedTime);
        return MapUtils.mapForList(this.list(wrapper), RoleItemOutput.class);
    }

    @Override
    public BasePageOutput<RoleItemOutput> getPage(RolePageInput input) {
        Page<RoleQuery> page = this.baseMapper.getPage(Page.of(input.getCurrent(), input.getSize()),
                input, null);
        return PageUtils.buildBasePage(page, RoleItemOutput.class);
    }

    @Override
    public void authorize(AuthorizationInput input) {
        // 校验角色
        Role role = this.checkRole(input.getId());
        ExceptionUtils.check(Objects.equals(Constants.SUPER_ROLE, role.getId()), "超级管理员不可授权");
        // 删除角色权限数据
        this.rolePermissionService.deleteByRoleId(input.getId());
        // 批量添加角色权限
        List<RolePermission> permissionList =MapUtils.mapForList(input.getPermissions(), RolePermission.class);
        if (!permissionList.isEmpty()) {
            permissionList.forEach(e -> e.setRoleId(input.getId()));
        }
        this.rolePermissionService.addBatch(permissionList);

        // 更新角色用户权限信息
        List<UserRole> userList = this.userRoleService.getListByRoleId(input.getId());
        if (CollUtil.isNotEmpty(userList)) {
            userList.forEach(e -> StpUtil.getSessionByLoginId(e.getId()).delete(SaSession.PERMISSION_LIST));
        }
    }

    @Override
    public Role checkRole(Long roleId) {
        Role role = this.getById(roleId);
        ExceptionUtils.check(Objects.isNull(role), "非法角色 : " + roleId);
        return role;
    }

    @Override
    public List<Role> getListByRoleIds(List<Long> roleIdList) {
        List<Role> roleList = this.listByIds(roleIdList);
        if (CollUtil.isNotEmpty(roleList)) {
            return roleList.stream()
                    .filter(role -> role.getStatus().equals(StatusEnum.ENABLE.getCode()))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }


    /**
     * 根据角色名称查询角色信息
     * @param organizationId 组织id
     * @param name 角色名称
     * @return Role
     */
    private Role getRoleByName(Long organizationId, String name) {
        return new LambdaQueryChainWrapper<>(baseMapper)
                .eq(Role::getName, name)
                .eq(Role::getOrganizationId, organizationId)
                .one();
    }
}
