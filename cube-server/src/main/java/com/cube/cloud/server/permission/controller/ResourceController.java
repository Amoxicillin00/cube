package com.cube.cloud.server.permission.controller;

import com.cube.cloud.core.application.model.BaseIdInput;
import com.cube.cloud.core.application.model.BasePageOutput;
import com.cube.cloud.core.log.OperateType;
import com.cube.cloud.core.log.annotation.Log;
import com.cube.cloud.server.permission.model.*;
import com.cube.cloud.server.permission.service.ResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created with IntelliJ IDEA.
 * 模块资源管理
 * @author Long
 * @date 2024-06-07 15:27
 */
@RestController
@RequestMapping("/sys/resource")
@Api(tags = "模块资源管理")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    /**
     * 添加资源权限
     * @param input 添加参数
     */
    @ApiOperation("添加模块资源")
    @PostMapping("/create")
    @Log(name = "添加模块资源", type = OperateType.CREATE)
    public void add(@Valid @RequestBody PermissionAddInput input) {
    }

    /**
     * 删除资源权限
     * @param input 资源权限id参数
     */
    @ApiOperation("删除模块资源")
    @PostMapping("/delete")
    @Log(name = "删除模块资源", type = OperateType.DELETE)
    public void delete(@Valid @RequestBody BaseIdInput input) {
    }

    /**
     * 修改资源权限
     * @param input 修改参数
     */
    @ApiOperation("修改模块资源")
    @PostMapping("/update")
    @Log(name = "修改模块资源", type = OperateType.UPDATE)
    public void update(@Valid @RequestBody PermissionModifyInput input) {
    }

    /**
     * 资源权限详情
     * @param input 资源权限id参数
     * @return 资源权限详情
     */
    @ApiOperation("查询模块资源详情")
    @PostMapping("/details")
    @Log(name = "查询模块资源详情", type = OperateType.SELECT)
    public PermissionDetailsOutput details(@Valid @RequestBody BaseIdInput input) {
        return null;
    }

    /**
     * 分页查询资源权限列表
     * @param input 查询参数
     * @return 资源权限分页列表
     */
    @ApiOperation("查询模块资源列表-分页查询")
    @PostMapping("/page")
    @Log(name = "查询权限资源列表-分页查询", type = OperateType.SELECT)
    public BasePageOutput<PermissionItemOutput> page(@Valid @RequestBody PermissionPageInput input) {
        return null;
    }
}
