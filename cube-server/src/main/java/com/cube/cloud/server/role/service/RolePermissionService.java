package com.cube.cloud.server.role.service;

import com.cube.cloud.server.role.entity.RolePermission;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * 角色权限
 * @author Long
 * @date 2023-05-26 15:49
 */
public interface RolePermissionService {

    /**
     * 删除角色权限数据
     * @param roleId 角色id
     */
    void deleteByRoleId(Long roleId);

    /**
     * 批量添加角色权限
     * @param permissionList 角色权限集合
     */
    void addBatch(List<RolePermission> permissionList);

    /**
     * 获取权限列表
     * @param roleId 角色id
     * @return 权限列表
     */
    List<String> getListByRoleId(Long roleId);

    /**
     * 超级管理员添加权限
     * @param permissionId 权限id
     */
    void addAdminPermission(Long permissionId);

    /**
     * 根据资源权限id删除角色相关权限
     * @param permissionId 资源权限id
     */
    void deleteByPermissionId(Long permissionId);

}
