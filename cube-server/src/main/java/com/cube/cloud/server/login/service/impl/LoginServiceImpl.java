package com.cube.cloud.server.login.service.impl;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollUtil;
import com.cube.cloud.core.application.dto.CurrentUser;
import com.cube.cloud.core.application.model.BaseIdInput;
import com.cube.cloud.core.constants.Constants;
import com.cube.cloud.core.exception.ExceptionUtils;
import com.cube.cloud.core.redis.RedisUtils;
import com.cube.cloud.core.util.MapUtils;
import com.cube.cloud.core.web.context.BaseContext;
import com.cube.cloud.server.login.model.LoginInput;
import com.cube.cloud.server.login.model.LoginOutput;
import com.cube.cloud.server.login.service.LoginService;
import com.cube.cloud.server.organization.entity.Organization;
import com.cube.cloud.server.organization.service.OrganizationService;
import com.cube.cloud.server.role.entity.Role;
import com.cube.cloud.server.role.service.RoleService;
import com.cube.cloud.server.security.service.SecurityService;
import com.cube.cloud.server.user.entity.User;
import com.cube.cloud.server.user.entity.UserRole;
import com.cube.cloud.server.user.service.UserRoleService;
import com.cube.cloud.server.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * 登录服务
 * @author Long
 * @date 2023-05-18 16:04
 */
@Service
public class LoginServiceImpl implements LoginService {

    private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Resource
    private RedisUtils redisUtils;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private OrganizationService organizationService;



    @Override
    public LoginOutput doLogin(LoginInput input, HttpServletRequest request) {
        // 校验账号
        User user = this.userService.getUserByAccount(input.getAccount());
        if (Objects.isNull(user)) {
            ExceptionUtils.check(true, "账号或密码错误 : " + input.getAccount());
        }
        // 校验所属组织信息
        Organization organization = this.organizationService.getOrganizationById(user.getOrganizationId());
        if (Objects.isNull(organization)) {
            ExceptionUtils.check(true, "用户所属组织异常 : " + user.getOrganizationId());
        }
        // 校验用户密码
        this.verifyForPassword(request.getSession().getId(), user, input.getPassword());
        // 用户角色信息
        List<UserRole> userRoleList = this.userRoleService.getListByUserId(user.getId());
        // TODO 数据权限

        // 登录
        StpUtil.login(user.getId());
        // 当前用户信息
        CurrentUser currentUser = this.buildCurrentUser(user, organization, userRoleList);
        StpUtil.getSession().set(SaSession.USER, currentUser);

        return this.buildLoginOutput(StpUtil.getTokenInfo(), currentUser);
    }

    @Override
    public void doLogout() {
        logger.info("用户 : " + BaseContext.getCurrentUser().getName() + "(" +StpUtil.getLoginIdAsLong() + ") 退出登录");
        StpUtil.logout(StpUtil.getLoginIdAsLong());
    }

    @Override
    public void doKickOut(BaseIdInput input) {
        logger.info(BaseContext.getCurrentUser().getName() + "将用户 : " + input.getId() + " 踢下线了" );
        StpUtil.kickout(input.getId());
    }

    /**
     * 构建当前用户信息
     * @param user 用户信息
     * @param organization 组织信息
     * @param userRoleList 用户角色列表
     * @return 当前用户信息
     */
    private CurrentUser buildCurrentUser(User user, Organization organization, List<UserRole> userRoleList) {
        // 用户信息
        CurrentUser currentUser = MapUtils.map(user, CurrentUser.class);
        // 所属组织信息
        currentUser.setOrganizationId(organization.getId());
        currentUser.setOrganizationName(organization.getName());
        // 角色信息
        if (CollUtil.isNotEmpty(userRoleList)) {
            List<Long> roleIdList = userRoleList.stream().map(UserRole::getRoleId).collect(Collectors.toList());
            List<Role> roleList = this.roleService.getListByRoleIds(roleIdList);
            if (CollUtil.isNotEmpty(roleList)) {
                currentUser.setRoles(roleList.stream().map(Role::getName).collect(Collectors.joining(",")));
            } else {
                currentUser.setRoles("");
            }
            // 用户角色id列表
            StpUtil.getSession().set(SaSession.ROLE_LIST,
                    roleIdList.stream().map(String::valueOf).collect(Collectors.toList()));
        }
        return currentUser;
    }

    /**
     * 构建登录成功输出信息
     * @param token token票据
     * @param currentUser 当前用户信息
     * @return 登录成功输出信息
     */
    private LoginOutput buildLoginOutput(SaTokenInfo token, CurrentUser currentUser) {
        LoginOutput loginOutput = MapUtils.map(currentUser, LoginOutput.class);
        // 用户id
        loginOutput.setId(StpUtil.getLoginIdAsLong());
        // token
        loginOutput.setToken(token.getTokenValue());
        loginOutput.setTokenName(token.getTokenName());

        return loginOutput;
    }

    /**
     * 校验用户密码
     * @param sessionId 请求sessionId
     * @param user 用户信息
     * @param verifyPassword 需要校验的密码
     */
    private void verifyForPassword(String sessionId, User user, String verifyPassword) {
        // 密码错误次数限制
        String key = Constants.ERROR_COUNT + sessionId + "-" + user.getId();
        Integer errorCount = this.redisUtils.get(key, Integer.class);
        if (Objects.isNull(errorCount)) {
            errorCount = 0;
        }
        ExceptionUtils.check(errorCount >= 5, "密码错误次数已达上限 : " + user.getAccount());
        String password = this.securityService.decryptByPrivateKey(verifyPassword);
        if (!securityService.matchesByMD5(user, password)) {
            errorCount++;
            this.redisUtils.set(key, errorCount, 5 * 60);
            ExceptionUtils.throwValidationException("账号或密码错误 : " + user.getAccount());
        }
    }
}
