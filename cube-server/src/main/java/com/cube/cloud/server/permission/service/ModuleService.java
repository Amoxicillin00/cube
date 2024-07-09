package com.cube.cloud.server.permission.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cube.cloud.core.application.model.BaseIdInput;
import com.cube.cloud.core.application.model.BasePageOutput;
import com.cube.cloud.core.application.model.BaseStatusInput;
import com.cube.cloud.server.permission.entity.Module;
import com.cube.cloud.server.permission.model.*;

/**
 * Created with IntelliJ IDEA.
 * 模块管理业务层
 * @author Long
 * @date 2024-06-07 14:57
 */
public interface ModuleService extends IService<Module> {

    /**
     * 添加资源模块
     * @param input 资源模块参数
     */
    void add(ModuleAddInput input);

    /**
     * 删除资源模块
     * @param input 输入参数
     */
    void delete(BaseIdInput input);

    /**
     * 更新资源模块
     * @param input 输入参数
     */
    void update(ModuleModifyInput input);

    /**
     * 是否需要授权(false : 不需要、true : 需要)
     * @param input 输入参数
     */
    void updateIsAuthorize(ModuleIsAuthorizeInput input);

    /**
     * 修改资源模块状态(0 : 禁用、1 : 启用)
     * @param input 输入参数
     */
    void updateStatus(BaseStatusInput input);

    /**
     * 获取资源模块分页列表
     * @param input 输入参数
     * @return 资源模块分页列表
     */
    BasePageOutput<ModuleItemOutput> getPage(ModulePageInput input);

    /**
     * 校验资源模块是否存在
     * @param moduleId 资源模块id
     * @return 资源模块信息
     */
    Module CheckModule(Long moduleId);
}
