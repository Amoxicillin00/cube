package com.cube.cloud.server.role.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cube.cloud.server.role.entity.Role;
import com.cube.cloud.server.role.model.RolePageInput;
import com.cube.cloud.server.role.query.RoleQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created with IntelliJ IDEA.
 * 角色Mapper
 * @author Long
 * @date 2023-01-04 11:01
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 获取角色详细信息
     * @param roleId 角色id
     * @return 角色详细信息
     */
    RoleQuery getDetails(@Param("roleId") Long roleId);

    /**
     * 分页查询
     * @param page 分页数据
     * @param input 查询条件
     * @param organizationId 组织id
     * @return Page<RoleQuery>
     */
    Page<RoleQuery> getPage(@Param("page") Page<?> page, @Param("input") RolePageInput input, @Param("organizationId") Long organizationId);
}
