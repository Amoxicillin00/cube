package com.cube.cloud.server;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollUtil;
import com.cube.cloud.core.filter.CommonPermissions;
import com.cube.cloud.server.role.entity.Role;
import com.cube.cloud.server.role.service.RolePermissionService;
import com.cube.cloud.server.role.service.RoleService;
import com.cube.cloud.server.user.entity.UserRole;
import com.cube.cloud.server.user.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * 自定义权限验证接口拓展
 * @author Long
 * @date 2023-04-06 16:01
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RolePermissionService rolePermissionService;


    /**
     * 获取用户权限集合(角色权限+用户权限)
     * @param loginId 用户id
     * @param loginType 登录类型
     * @return 权限列表
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 权限列表
        return StpUtil.getSessionByLoginId(loginId).get(SaSession.PERMISSION_LIST, () -> {
            // 公共权限
            List<String> permissionList = CommonPermissions.commonPermissions();
            // 角色权限
            List<String> roleIdList = this.getRoleList(loginId, loginType);
            if (CollUtil.isNotEmpty(roleIdList)) {
                for (String roleId : roleIdList) {
                    permissionList.addAll(this.rolePermissionService.getListByRoleId(Long.parseLong(roleId)));
                }
                return permissionList.stream().distinct().collect(Collectors.toList());
            }
            return new ArrayList<String>();
        });
    }

    /**
     * 获取用户角色集合
     * @param loginId 用户id
     * @param loginType 登录类型
     * @return 角色列表
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        // 角色列表
        return StpUtil.getSessionByLoginId(loginId).get(SaSession.ROLE_LIST, () -> {
            // 从数据库查询这个用户所拥有的角色列表
            List<UserRole> userRoleList = this.userRoleService.getListByUserId(Long.parseLong(loginId.toString()));
            // 角色id列表
            List<Long> roleIdList = userRoleList.stream()
                    .map(UserRole::getRoleId)
                    .collect(Collectors.toList());

            return this.roleService.getListByRoleIds(roleIdList).stream()
                    .map(Role::getId)
                    .map(String::valueOf)
                    .collect(Collectors.toList());
        });
    }
}
