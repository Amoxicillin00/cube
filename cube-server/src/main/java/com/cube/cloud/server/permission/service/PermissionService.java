package com.cube.cloud.server.permission.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cube.cloud.core.application.model.BaseIdInput;
import com.cube.cloud.core.application.model.BasePageOutput;
import com.cube.cloud.server.permission.entity.Permission;
import com.cube.cloud.server.permission.model.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * 资源权限管理业务层
 * @author Long
 * @date 2023-06-07 14:42
 */
public interface PermissionService extends IService<Permission> {

    /**
     * 添加资源权限
     * @param input 添加参数
     */
    void add(PermissionAddInput input);

    /**
     * 删除资源权限
     * @param input 资源权限id参数
     */
    void delete(BaseIdInput input);

    /**
     * 修改资源权限
     * @param input 修改参数
     */
    void update(PermissionModifyInput input);

    /**
     * 获取资源权限分页列表
     * @param input 查询参数
     * @return 资源权限分页列表
     */
    BasePageOutput<PermissionItemOutput> getPage(PermissionPageInput input);

    /**
     * 获取资源权限树
     * @return 资源权限树
     */
    List<PermissionTreeOutput> getTree();
}
