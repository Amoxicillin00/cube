package com.cube.cloud.server.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cube.cloud.server.role.entity.Role;
import com.cube.cloud.server.user.entity.UserRole;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-06-07 9:35
 */
public interface UserRoleService extends IService<UserRole> {

    /**
     * 添加用户角色关联关系
     * @param userId 用户id
     * @param roleIdList 角色列表
     */
    void addBatch(Long userId, List<Long> roleIdList);

    /**
     * 删除用户角色关联关系
     * @param userId 用户id
     */
    void deleteByUserId(Long userId);

    /**
     * 获取用户的角色信息
     * @param userId 用户id
     * @return 用户的角色信息
     */
    List<UserRole> getListByUserId(Long userId);

    /**
     * 获取角色用户列表
     * @param roleId 角色id
     * @return 角色用户列表
     */
    List<UserRole> getListByRoleId(Long roleId);

    /**
     * 获取角色用户列表
     * @param roleIdList 角色idl列表
     * @return 角色用户列表
     */
    List<UserRole> getListByRoleIdList(List<Long> roleIdList);

}
