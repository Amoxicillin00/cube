package com.cube.cloud.server.role.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cube.cloud.server.role.entity.RolePermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * 角色权限Mapper
 * @author Long
 * @date 2023-05-26 15:44
 */
@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

    /**
     * 获取操作权限列表
     * @param roleId 角色id
     * @return 权限列表
     */
    List<String> getPermissionListByRoleId(@Param("roleId") Long roleId);
}
