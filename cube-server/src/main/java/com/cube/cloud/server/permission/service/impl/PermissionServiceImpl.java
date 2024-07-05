package com.cube.cloud.server.permission.service.impl;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cube.cloud.core.application.model.BaseIdInput;
import com.cube.cloud.core.application.model.BasePageOutput;
import com.cube.cloud.core.constants.Constants;
import com.cube.cloud.core.enums.PermissionTypeEnum;
import com.cube.cloud.core.exception.ExceptionUtils;
import com.cube.cloud.core.util.MapUtils;
import com.cube.cloud.core.util.PageUtils;
import com.cube.cloud.core.util.TreeUtils;
import com.cube.cloud.server.permission.entity.Permission;
import com.cube.cloud.server.permission.mapper.PermissionMapper;
import com.cube.cloud.server.permission.model.*;
import com.cube.cloud.server.permission.query.PermissionQuery;
import com.cube.cloud.server.permission.service.PermissionService;
import com.cube.cloud.server.role.service.RolePermissionService;
import com.cube.cloud.server.user.entity.UserRole;
import com.cube.cloud.server.user.service.UserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-06-07 14:42
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission>
        implements PermissionService {

    private static final Logger logger = LoggerFactory.getLogger(PermissionServiceImpl.class);






    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(PermissionAddInput input) {
        /*Permission permission = MapUtils.map(input, Permission.class);
        // 校验父级节点，并生成路由标识，0为根节点
        if (Objects.equals(0L, permission.getParentId())) {
            permission.setLevel(1);
        } else {
            Permission parentPermission = this.getById(permission.getParentId());
            ExceptionUtils.check(Objects.isNull(parentPermission), "父级资源不存在 : " + permission.getParentId());
            if (Objects.equals(PermissionTypeEnum.BUTTON.getCode(), permission.getType())) {
                permission.setRoute(parentPermission.getRoute() + "_button_" + permission.getRoute());
            } else {
                permission.setRoute(parentPermission.getRoute() + "_" +permission.getRoute());
            }
            permission.setLevel(parentPermission.getLevel() + 1);
            permission.setFullId(parentPermission.getFullId());
        }
        // 校验资源是否已存在
        LambdaQueryWrapper<Permission> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Permission::getName, permission.getName())
                .eq(Permission::getRoute, permission.getRoute())
                .eq(Permission::getType, permission.getType())
                .eq(Permission::getClientType, permission.getClientType());
        ExceptionUtils.check(Objects.nonNull(this.getOne(wrapper)), "资源已存在 : " + permission.getName());
        // 校验权限是否已存在
        wrapper.clear();
        if (Objects.equals(PermissionTypeEnum.PERMISSION.getCode(), permission.getType())) {
            ExceptionUtils.check(StrUtil.isBlankIfStr(permission.getPath()), "权限路径不能为空");
            wrapper.eq(Permission::getPath, permission.getPath());
            ExceptionUtils.check(Objects.nonNull(this.getOne(wrapper)), "资源已存在 : " + permission.getPath());
        } else {
            permission.setPath("/");
        }
        // 保存
        this.save(permission);
        // 更新fullId
        if (Objects.isNull(permission.getFullId())) {
            permission.setFullId(String.valueOf(permission.getId()));
        } else {
            permission.setFullId(permission.getFullId() + "," + permission.getId());
        }
        this.updateById(permission);
        // 给超级管理员增加权限
        this.rolePermissionService.addAdminPermission(permission.getId());
        // 更新超级管理员Sa-Session的权限列表
        if (CollUtil.isNotEmpty(StpUtil.getTokenValueListByLoginId(Constants.SUPER_ADMIN))) {
            StpUtil.getSessionByLoginId(Constants.SUPER_ADMIN).delete(SaSession.PERMISSION_LIST);
        }*/
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(BaseIdInput input) {
        /*// 校验资源
        Permission permission = this.checkPermission(input.getId());
        List<Long> permissionIdList =  new ArrayList<>();
        this.deleteChild(permission.getId(), permissionIdList);
        // 角色id列表
        List<Long> roleIdList = this.rolePermissionService.getRoleIdListAndDelete(permissionIdList);
        List<UserRole> userRoleList = this.userRoleService.getListByRoleIdList(roleIdList);
        // SaSession操作权限处理
        if (CollUtil.isNotEmpty(userRoleList)) {
            userRoleList.forEach(e -> {
                // 判断是否登录 登录时会有token
                if (CollUtil.isNotEmpty(StpUtil.getTokenValueListByLoginId(e.getUserId()))) {
                    StpUtil.getSessionByLoginId(e.getUserId()).delete(SaSession.PERMISSION_LIST);
                }
            });
        }*/
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(PermissionModifyInput input) {
        /*// 校验资源
        Permission permission = this.checkPermission(input.getId());
        // 校验权限名称
        LambdaQueryWrapper<Permission> wrapper = Wrappers.lambdaQuery();
        wrapper.ne(Permission::getId, permission.getId())
                .eq(Permission::getName, input.getName())
                .eq(Permission::getRoute, permission.getRoute())
                .eq(Permission::getType, permission.getType())
                .eq(Permission::getClientType, permission.getClientType());
        ExceptionUtils.check(Objects.nonNull(this.getOne(wrapper)),
                "资源名称已重复 : " + permission.getName());

        // 更新value
        BeanUtils.copyProperties(input, permission);
        // 若是更改父节点，则需要更新本身以及下级节点的fullId
        if (Objects.nonNull(input.getParentId()) && !Objects.equals(permission.getParentId(), input.getParentId())) {
            String oldFullId = permission.getFullId();
            if (Objects.equals(0L, permission.getParentId())) {
                permission.setLevel(1);
                permission.setFullId(String.valueOf(permission.getId()));
            } else {
                Permission parentPermission = this.getById(permission.getParentId());
                ExceptionUtils.check(Objects.isNull(parentPermission),
                        "父级资源不存在 : " + permission.getParentId());
                ExceptionUtils.check(parentPermission.getFullId().contains(String.valueOf(permission.getId())),
                        "不能移到当前资源下");
                permission.setLevel(parentPermission.getLevel() + 1);
                permission.setFullId(parentPermission.getFullId() + "," + permission.getId());
            }
            // 更新当前节点下的子节点fullId
            List<Permission> childList = this.getChildList(permission.getId());
            childList.forEach(e -> e.setFullId(e.getFullId().replace(oldFullId, permission.getFullId())));
            this.updateBatchById(childList);
        }
        // 更新当前节点
        this.updateById(permission);*/
    }

    @Override
    public BasePageOutput<PermissionItemOutput> getPage(PermissionPageInput input) {
        Page<PermissionQuery> page = this.baseMapper.getPage(Page.of(input.getCurrent(), input.getSize()), input);
        return PageUtils.buildBasePage(page, PermissionItemOutput.class);
    }

    @Override
    public List<PermissionTreeOutput> getTree() {
        return TreeUtils.buildBaseTree(0L, this.list(), PermissionTreeOutput.class);
    }

    /**
     * 递归删除子节点权限
     * @param permissionId 权限id
     */
    private void deleteChild(Long permissionId, List<Long> permissionIdList) {
        /*permissionIdList.add(permissionId);
        LambdaQueryWrapper<Permission> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Permission::getParentId, permissionId);
        List<Permission> permissionList = this.list(wrapper);
        if (CollUtil.isNotEmpty(permissionList)) {
            permissionList.forEach(e -> deleteChild(e.getId(), permissionIdList));
        }
        this.removeById(permissionId);*/
    }

    /**
     * 从上至下递归权限节点
     * @param permissionId 递归开始权限id
     * @return 权限列表
     */


}
