package com.cube.cloud.server.permission.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cube.cloud.server.permission.entity.Permission;
import com.cube.cloud.server.permission.model.PermissionPageInput;
import com.cube.cloud.server.permission.query.PermissionQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-06-07 14:41
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 获取资源权限详细信息
     * @param permissionId 权限id
     * @return 资源权限详细信息
     */
    PermissionQuery getDetails(@Param("permissionId") Long permissionId);

    /**
     * 分页查询
     * @param page 分页对象
     * @param input 查询条件
     * @return 资源权限结果集
     */
    Page<PermissionQuery> getPage(@Param("page") Page<?> page, @Param("input") PermissionPageInput input);
}
