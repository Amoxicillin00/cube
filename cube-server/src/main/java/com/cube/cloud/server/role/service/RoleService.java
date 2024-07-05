package com.cube.cloud.server.role.service;

import com.cube.cloud.core.application.model.BaseIdInput;
import com.cube.cloud.core.application.model.BasePageOutput;
import com.cube.cloud.server.role.entity.Role;
import com.cube.cloud.server.role.model.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * 角色
 * @author Long
 * @date 2023-01-04 11:02
 */
public interface RoleService {

    /**
     * 添加
     * @param input 输入参数
     */
    void add(RoleAddInput input);

    /**
     * 删除
     * @param input 主键id
     */
    void delete(BaseIdInput input);

    /**
     * 修改
     * @param input 修改参数
     */
    void update(RoleModifyInput input);

    /**
     * 详情
     * @param input 主键id
     * @return RoleDetailsOutput
     */
    RoleDetailsOutput getDetails(BaseIdInput input);

    /**
     * 列表查询
     * @return List<RoleItemOutput>
     */
    List<RoleItemOutput> getList(RoleListInput input);

    /**
     * 分页查询
     * @param input 查询参数
     * @return BasePageOutput<RoleItemOutput>
     */
    BasePageOutput<RoleItemOutput> getPage(RolePageInput input);

    /**
     * 授权
     * @param input 授权信息
     */
    void authorize(AuthorizationInput input);

    /**
     * 校验角色是否合法
     * @param roleId 角色id
     */
    Role checkRole(Long roleId);

    /**
     * 获取角色信息
     * @param roleIdList 角色id列表
     * @return 角色信息列表
     */
    List<Role> getListByRoleIds(List<Long> roleIdList);
}
