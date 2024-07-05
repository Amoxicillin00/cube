package com.cube.cloud.server.user.service.impl;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cube.cloud.core.application.dto.CurrentUser;
import com.cube.cloud.core.application.model.BaseIdInput;
import com.cube.cloud.core.application.model.BasePageOutput;
import com.cube.cloud.core.application.model.BaseStatusInput;
import com.cube.cloud.core.constants.Constants;
import com.cube.cloud.core.enums.StatusEnum;
import com.cube.cloud.core.exception.ExceptionUtils;
import com.cube.cloud.core.util.MapUtils;
import com.cube.cloud.core.util.PageUtils;
import com.cube.cloud.core.web.context.BaseContext;
import com.cube.cloud.server.organization.entity.Organization;
import com.cube.cloud.server.organization.service.OrganizationService;
import com.cube.cloud.server.role.entity.Role;
import com.cube.cloud.server.role.service.RoleService;
import com.cube.cloud.server.security.service.SecurityService;
import com.cube.cloud.server.user.entity.User;
import com.cube.cloud.server.user.entity.UserRole;
import com.cube.cloud.server.user.mapper.UserMapper;
import com.cube.cloud.server.user.model.*;
import com.cube.cloud.server.user.query.UserQuery;
import com.cube.cloud.server.user.service.UserRoleService;
import com.cube.cloud.server.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
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
 * 用户管理服务层
 * @author Long
 * @date 2023-01-04 10:55
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private OrganizationService organizationService;



    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(UserAddInput input) {
        // 校验密码是否为空
        String password = this.securityService.decryptByPrivateKey(input.getPassword());
        ExceptionUtils.check(Objects.isNull(password), "登录密码不能为空");
        // 校验组织是否存在
        ExceptionUtils.check(Objects.isNull(this.organizationService.getOrganizationById(input.getOrganizationId())),
                "非法组织 : " + input.getOrganizationId());
        // 校验账号
        ExceptionUtils.check(this.checkLoginAccount(input.getAccount(), input.getOrganizationId()),
                "账号已存在 : " + input.getAccount());
        // 校验电话号码
        ExceptionUtils.check(this.checkPhoneNumber(input.getPhoneNumber(), input.getOrganizationId(), null),
                "电话号码已存在 : " +input.getPhoneNumber());
        // 校验角色是否合法
        ExceptionUtils.check(input.getRoles().contains(Constants.SUPER_ROLE), "超级管理员不予授权");
        input.getRoles().forEach(roleId -> this.roleService.checkRole(roleId));

        // 新增用户信息
        User user = MapUtils.map(input, User.class);
        user.setStatus(StatusEnum.ENABLE.getCode());
        user.setNickName(this.generateNickName(input.getPhoneNumber()));
        user.setPassword(password);
        // 添加新用户信息
        this.save(user);
        // 更新密码
        this.updatePassword(user, password);
        //添加角色信息
        this.userRoleService.addBatch(user.getId(), input.getRoles());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(BaseIdInput input) {
        User user = this.checkUser(input.getId());
        ExceptionUtils.check(Objects.equals(Constants.SUPER_ADMIN, user.getId()), "超级管理员不可删除");
        // 删除用户信息
        this.removeById(user.getId());
        // 删除用户角色关联关系
        this.userRoleService.deleteByUserId(user.getId());
        // 踢出登录
        StpUtil.kickout(user.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(UserModifyInput input) {
        // 校验用户信息
        User user = this.checkUser(input.getId());
        // 校验电话号码
        ExceptionUtils.check(this.checkPhoneNumber(input.getPhoneNumber(), input.getOrganizationId(), user.getId()),
                "电话号码已存在 : " +input.getPhoneNumber());
        // 校验组织是否存在
        Organization organization = this.organizationService.getOrganizationById(input.getOrganizationId());
        ExceptionUtils.check(Objects.isNull(organization), "非法组织 : " + input.getOrganizationId());
        //更新用户信息
        BeanUtils.copyProperties(input, user);
        this.updateById(user);
        // 更新用户角色关联关系
        this.userRoleService.deleteByUserId(user.getId());
        this.userRoleService.addBatch(user.getId(), input.getRoles());

        List<UserRole> userRoleList = this.userRoleService.getListByUserId(user.getId());
        List<Long> roleIds = userRoleList.stream().map(UserRole::getRoleId).collect(Collectors.toList());

        // 更新SaSession
        this.updateSaSession(user, organization, this.roleService.getListByRoleIds(roleIds));
    }

    @Override
    public UserDetailsOutput getDetails(BaseIdInput input) {
        // 校验用户信息
        this.checkUser(input.getId());
        UserQuery userQuery = this.baseMapper.getDetails(input.getId());
        UserDetailsOutput output = MapUtils.map(userQuery, UserDetailsOutput.class);
        // 用户角色信息
        List<UserRole> userRoleList = this.userRoleService.getListByUserId(input.getId());
        List<Long> roleIds = userRoleList.stream().map(UserRole::getRoleId).collect(Collectors.toList());
        List<Role> roleList = this.roleService.getListByRoleIds(roleIds);
        // 角色信息
        if (CollUtil.isNotEmpty(roleList)) {
            output.setRoleNameList(roleList.stream().map(Role::getName).collect(Collectors.joining(",")));
            output.setRoleIdList(roleList.stream().map(Role::getId).collect(Collectors.toList()));
        } else {
            output.setRoleNameList("");
            output.setRoleIdList(new ArrayList<>());
        }
        return output;
    }

    @Override
    public List<UserItemOutput> getList() {
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(User::getStatus, StatusEnum.ENABLE.getCode())
                .eq(User::getOrganizationId, BaseContext.getCurrentUser().getOrganizationId())
                .orderByDesc(User::getCreatedTime);
        return MapUtils.mapForList(this.list(wrapper), UserItemOutput.class);
    }

    @Override
    public BasePageOutput<UserItemOutput> getPage(UserPageInput input) {
        // 分页查询结果集
        Page<UserQuery> page = this.baseMapper.getPage(Page.of(input.getCurrent(), input.getSize()),
                input, BaseContext.getCurrentUser().getOrganizationId());
        BasePageOutput<UserItemOutput> userItemPage = PageUtils.buildBasePage(page, UserItemOutput.class);
        // 角色信息
        userItemPage.getRecords().forEach(e -> {
            // 用户角色信息
            List<UserRole> userRoleList = this.userRoleService.getListByUserId(e.getId());
            List<Long> roleIdList = userRoleList.stream().map(UserRole::getRoleId).collect(Collectors.toList());
            List<Role> roleList = this.roleService.getListByRoleIds(roleIdList);
            if (CollUtil.isNotEmpty(roleList)) {
                e.setRoleNameList(roleList.stream().map(Role::getName).collect(Collectors.joining(",")));
            } else {
                e.setRoleNameList("");
            }
        });
        return userItemPage;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePassword(UpdatePasswordInput input) {
        // 检查用户信息
        User user = this.getById(StpUtil.getLoginIdAsLong());
        ExceptionUtils.check(Objects.isNull(user), "无法更新密码");
        // 校验旧密码
        if (!this.securityService.matchesByMD5(user,
                this.securityService.decryptByPrivateKey(input.getOldPassword()))) {
            ExceptionUtils.throwValidationException("旧密码不正确");
        }
        // 更新密码
        this.updatePassword(user, this.securityService.decryptByPrivateKey(input.getNewPassword()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateStatus(BaseStatusInput input) {
        // 检查用户信息
        User user = this.getById(input.getId());
        ExceptionUtils.check(Objects.equals(Constants.SUPER_ADMIN, user.getId()), "超级管理员不可禁用");
        // 校验状态枚举
        ExceptionUtils.check(!StatusEnum.hasCode(input.getStatus()), "状态枚举错误 : " + input.getStatus());
        // 修改状态
        user.setStatus(input.getStatus());
        this.updateById(user);

        // 对禁用状态进行踢下线处理
        if (Objects.equals(user.getStatus(), StatusEnum.DISABLE.getCode())) {
            log.info(BaseContext.getCurrentUser().getName() + "将用户 : " +user.getName() + "【" + user.getId() + "】 禁用并踢下线了" );
            StpUtil.kickout(user.getId());
        }
    }

    @Override
    public User getUserByAccount(String loginAccount) {
        return new LambdaQueryChainWrapper<>(baseMapper)
                .eq(User::getAccount, loginAccount)
                .eq(User::getStatus, StatusEnum.ENABLE.getCode())
                .one();
    }

    @Override
    public Boolean checkPhoneNumber(String phoneNumber, Long organizationId, Long userId) {
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(User::getPhoneNumber, phoneNumber)
                .eq(User::getOrganizationId, organizationId);
        if (Objects.nonNull(userId)) {
            wrapper.ne(User::getId, userId);
        }
        return Objects.nonNull(this.getOne(wrapper));
    }

    @Override
    public List<RoleUserOutput> getListByRoleId(Long roleId) {
        List<UserRole> userRoleList = this.userRoleService.getListByRoleId(roleId);
        List<Long> userIds = userRoleList.stream().map(UserRole::getUserId).collect(Collectors.toList());
        List<User> userList = this.listByIds(userIds);
        return MapUtils.mapForList(userList, RoleUserOutput.class);
    }

    /**
     * 用户默认昵称生成
     * @param phoneNumber 电话号码
     * @return 用户默认昵称
     */
    private String generateNickName(String phoneNumber) {
        StringBuilder nickName = new StringBuilder();
        nickName.append("匿名用户");
        nickName.append(phoneNumber);
        nickName.replace(7, 11, "****");
        return nickName.toString();
    }

    /**
     * 更新密码
     * @param user 用户信息
     * @param newPassword 新密码
     */
    private void updatePassword(User user, String newPassword) {
        String password =this.securityService.encodeByMD5(user, newPassword);
        ExceptionUtils.check(Objects.isNull(password), "密码不能为空");
        user.setPassword(password);
        this.updateById(user);
    }

    /**
     * 校验用户是否合法
     * @param userId 用户id
     * @return 用户信息
     */
    private User checkUser(Long userId) {
        User user = this.getById(userId);
        ExceptionUtils.check(Objects.isNull(user), "非法用户 : " + userId);
        return user;
    }

    /**
     * 校验登录账号唯一性
     * @param loginAccount 登录账号
     * @param organizationId 组织id
     * @return 登录账号是否唯一
     */
    private boolean checkLoginAccount(String loginAccount, Long organizationId) {
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(User::getAccount, loginAccount)
                .eq(User::getOrganizationId, organizationId);
        return Objects.nonNull(this.getOne(wrapper));
    }

    /**
     * 更新SaSession的dataMap数据
     * @param user 用户信息
     * @param organization 所属组织信息
     * @param roleList 用户角色列表
     */
    private void updateSaSession(User user, Organization organization, List<Role> roleList) {
        // 用户信息
        CurrentUser currentUser = MapUtils.map(user, CurrentUser.class);

        // 更新SaSession的用户角色列表
        if (CollUtil.isNotEmpty(roleList)) {
            // 角色信息
            if (BaseContext.isSuperAdmin(user.getId())) {
                currentUser.setRoles(Constants.SUPER_ADMIN_NAME);
            } else {
                currentUser.setRoles(roleList.stream().map(Role::getName).collect(Collectors.joining(",")));
            }

            StpUtil.getSession().set(SaSession.ROLE_LIST,
                    roleList.stream().map(Role::getId).map(String::valueOf).collect(Collectors.toList()));
        }

        // 删除SaSession的PERMISSION_LIST权限数据，在下一次调用接口会重新加载权限数据
        StpUtil.getSession().delete(SaSession.PERMISSION_LIST);

        // 更新SaSession用户信息
        currentUser.setOrganizationId(organization.getId());
        currentUser.setOrganizationName(organization.getName());
        StpUtil.getSession().set(SaSession.USER, currentUser);

    }
}
