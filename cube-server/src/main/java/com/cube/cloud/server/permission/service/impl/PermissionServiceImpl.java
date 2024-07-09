package com.cube.cloud.server.permission.service.impl;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cube.cloud.core.application.model.BaseIdInput;
import com.cube.cloud.core.application.model.BasePageOutput;
import com.cube.cloud.core.constants.Constants;
import com.cube.cloud.core.exception.ExceptionUtils;
import com.cube.cloud.core.util.MapUtils;
import com.cube.cloud.core.util.PageUtils;
import com.cube.cloud.core.util.StringUtils;
import com.cube.cloud.core.util.TreeUtils;
import com.cube.cloud.server.permission.entity.Permission;
import com.cube.cloud.server.permission.mapper.PermissionMapper;
import com.cube.cloud.server.permission.model.*;
import com.cube.cloud.server.permission.query.PermissionQuery;
import com.cube.cloud.server.permission.service.ModuleService;
import com.cube.cloud.server.permission.service.PermissionService;
import com.cube.cloud.server.role.service.RolePermissionService;
import com.cube.cloud.server.user.service.UserPermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

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

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private UserPermissionService userPermissionService;




    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(PermissionAddInput input) {
        Permission permission = MapUtils.map(input, Permission.class);
        // 校验参数
        CheckParameter(permission);
        // 保存
        this.save(permission);
        logger.info("添加资源权限成功！data : {}", permission);
        // 给超级管理员增加权限
        this.rolePermissionService.addAdminPermission(permission.getId());
        // 更新超级管理员Sa-Session的权限列表
        if (CollUtil.isNotEmpty(StpUtil.getTokenValueListByLoginId(Constants.SUPER_ADMIN))) {
            StpUtil.getSessionByLoginId(Constants.SUPER_ADMIN).delete(SaSession.PERMISSION_LIST);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(BaseIdInput input) {
        // 检查需要删除的资源权限是否存在
        Permission permission = CheckPermission(input.getId());
        // 逻辑删除
        this.removeById(permission.getId());
        logger.info("删除资源权限成功！data : {}", permission);

        // 删除角色权限相关数据
        this.rolePermissionService.deleteByPermissionId(permission.getId());
        // 删除用户权限相关数据
        this.userPermissionService.deleteByPermissionId(permission.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(PermissionModifyInput input) {
        Permission permission = CheckPermission(input.getId());
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
    public void deleteByModuleId(Long moduleId) {

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
     * 校验资源权限是否存在
     * @param permissionId 资源权限id
     * @return 资源权限信息
     */
    public Permission CheckPermission(Long permissionId) {
        Permission permission = this.getById(permissionId);
        ExceptionUtils.check(Objects.isNull(permission), "资源模块不存在 : " + permission);
        return permission;
    }

    /**
     * 参数校验
     * @param permission 资源权限
     */
    private void CheckParameter(Permission permission) {
        LambdaQueryWrapper<Permission> queryWrapper = new LambdaQueryWrapper<>();
        // 校验资源模块是否存在
        if (Objects.nonNull(permission.getModuleId())) {
            moduleService.CheckModule(permission.getModuleId());
        }
        // 校验唯一路由标识是否存在
        if (StringUtils.isNotNullOrBlank(permission.getRoute())) {
            queryWrapper.clear();

            queryWrapper.eq(Permission::getRoute, permission.getRoute());
            ExceptionUtils.check(Objects.nonNull(this.getOne(queryWrapper)), "资源权限唯一路由标识已存在 : " + permission.getRoute());
        }
    }
}
