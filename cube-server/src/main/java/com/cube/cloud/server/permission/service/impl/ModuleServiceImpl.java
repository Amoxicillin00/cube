package com.cube.cloud.server.permission.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cube.cloud.core.application.model.BaseIdInput;
import com.cube.cloud.core.application.model.BasePageOutput;
import com.cube.cloud.core.application.model.BaseStatusInput;
import com.cube.cloud.core.enums.StatusEnum;
import com.cube.cloud.core.exception.ExceptionUtils;
import com.cube.cloud.core.util.MapUtils;
import com.cube.cloud.core.util.PageUtils;
import com.cube.cloud.core.util.StringUtils;
import com.cube.cloud.server.permission.entity.Module;
import com.cube.cloud.server.permission.mapper.ModuleMapper;
import com.cube.cloud.server.permission.model.*;
import com.cube.cloud.server.permission.query.ModuleQuery;
import com.cube.cloud.server.permission.service.ModuleService;
import com.cube.cloud.server.permission.service.PermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2024-06-07 14:58
 */
@Service
public class ModuleServiceImpl extends ServiceImpl<ModuleMapper, Module> implements ModuleService {

    private static final Logger logger = LoggerFactory.getLogger(ModuleServiceImpl.class);


    @Autowired
    private PermissionService permissionService;




    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(ModuleAddInput input) {
        Module module = MapUtils.map(input, Module.class);
        // 校验参数
        CheckParameter(module);
        // 保存
        this.save(module);
        logger.info("添加资源模块成功！data : {}", module);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(BaseIdInput input) {
        // 检查需要删除的资源模块是否存在
        Module module = CheckModule(input.getId());

        // 逻辑删除
        this.removeById(module.getId());
        logger.info("删除资源模块成功！data : {}", module);

        // 删除资源模块下的资源权限
        this.permissionService.deleteByModuleId(module.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ModuleModifyInput input) {
        Module inputModule = MapUtils.map(input, Module.class);
        // 校验参数
        CheckParameter(inputModule);
        // 检查需要更新的资源模块是否存在
        Module module = CheckModule(input.getId());
        // 更新
        BeanUtils.copyProperties(inputModule, module);
        this.updateById(module);
        logger.info("更新资源模块成功！data : {}", module);
    }

    @Override
    public void updateIsAuthorize(ModuleIsAuthorizeInput input) {
        // 检查需要更新的资源模块是否存在
        Module module = CheckModule(input.getId());
        //TODO 异步任务处理
        if (!module.isAuthorize() && input.isAuthorize()) {
            //TODO 不需要授权转换为需要授权，需要对用户的权限进行处理

        } else if (module.isAuthorize() && !input.isAuthorize()) {
            //TODO 需要授权转换为不需要授权，需要对用户的权限进行处理
        }

        // 更新
        BeanUtils.copyProperties(input, module);
        this.updateById(module);
        logger.info("更新资源模块是否授权状态成功！data : {}", module);
    }

    @Override
    public void updateStatus(BaseStatusInput input) {
        // 检查需要更新的资源模块是否存在
        Module module = CheckModule(input.getId());
        //TODO 异步任务处理
        if (module.getStatus() == StatusEnum.DISABLE.getCode()
                && input.getStatus() == StatusEnum.ENABLE.getCode()) {
            //TODO 禁用转换为启用，需要对用户的权限进行处理

        } else if (module.getStatus() == StatusEnum.ENABLE.getCode()
                && input.getStatus() == StatusEnum.DISABLE.getCode())  {
            //TODO 启用转换为禁用，需要对用户的权限进行处理
        }

        // 更新
        BeanUtils.copyProperties(input, module);
        this.updateById(module);
        logger.info("更新资源模块启用状态成功！data : {}", module);
    }

    @Override
    public BasePageOutput<ModuleItemOutput> getPage(ModulePageInput input) {
        Page<ModuleQuery> page = this.baseMapper.selectModuleByPage(Page.of(input.getCurrent(), input.getSize()), input);
        return PageUtils.buildBasePage(page, ModuleItemOutput.class);
    }



    /**
     * 校验资源模块是否存在
     * @param moduleId 资源模块Id
     * @return Module
     */
    @Override
    public Module CheckModule(Long moduleId) {
        Module module = this.getById(moduleId);
        ExceptionUtils.check(Objects.isNull(module), "资源模块不存在 : " + moduleId);
        return module;
    }

    /**
     * 参数校验
     * @param module 资源模块
     */
    private void CheckParameter(Module module) {
        LambdaQueryWrapper<Module> queryWrapper = new LambdaQueryWrapper<>();
        // 校验模块类型下的模块名称是否存在
        if (StringUtils.isNotNullOrBlank(module.getName()) && Objects.nonNull(module.getType())) {
            queryWrapper.clear();

            queryWrapper.eq(Module::getName, module.getName())
                    .eq(Module::getType, module.getType());
            ExceptionUtils.check(Objects.nonNull(this.getOne(queryWrapper)), "资源模块已存在 : " + module.getName());
        }

        // 校验唯一路由标识是否存在
        if (StringUtils.isNotNullOrBlank(module.getRoute())) {
            queryWrapper.clear();

            queryWrapper.eq(Module::getRoute, module.getRoute());
            ExceptionUtils.check(Objects.nonNull(this.getOne(queryWrapper)), "资源模块唯一路由标识已存在 : " + module.getRoute());
        }

        // 校验父级id是否存在
        if (Objects.nonNull(module.getParentId())) {
            queryWrapper.clear();

            queryWrapper.eq(Module::getParentId, module.getParentId());
            ExceptionUtils.check(Objects.nonNull(this.getOne(queryWrapper)), "父级资源不存在 : " + module.getParentId());
        }

    }
}
