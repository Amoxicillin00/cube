package com.cube.cloud.server.permission.controller;

import com.cube.cloud.core.application.model.BaseIdInput;
import com.cube.cloud.core.application.model.BasePageOutput;
import com.cube.cloud.core.log.OperateType;
import com.cube.cloud.core.log.annotation.Log;
import com.cube.cloud.server.permission.model.*;
import com.cube.cloud.server.permission.service.PermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * 权限管理
 * @author Long
 * @date 2023-01-04 11:09
 */
@RestController
@RequestMapping("/sys/permission")
@Api(tags = "权限管理")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;


    /**
     * 添加资源权限
     * @param input 添加参数
     */
    @ApiOperation("添加权限")
    @PostMapping("/add")
    @Log(name = "添加权限", type = OperateType.CREATE)
    public void add(@Valid @RequestBody PermissionAddInput input) {
        this.permissionService.add(input);
    }

    /**
     * 删除资源权限
     * @param input 资源权限id参数
     */
    @ApiOperation("删除权限")
    @PostMapping("/delete")
    @Log(name = "删除权限", type = OperateType.DELETE)
    public void delete(@Valid @RequestBody BaseIdInput input) {
        this.permissionService.delete(input);
    }

    /**
     * 修改资源权限
     * @param input 修改参数
     */
    @ApiOperation("修改权限")
    @PostMapping("/update")
    @Log(name = "修改权限", type = OperateType.UPDATE)
    public void update(@Valid @RequestBody PermissionModifyInput input) {
        this.permissionService.update(input);
    }

    /**
     * 资源权限详情
     * @param input 资源权限id参数
     * @return 资源权限详情
     */
    @ApiOperation("查询权限详情")
    @PostMapping("/details")
    @Log(name = "查询权限详情", type = OperateType.SELECT)
    public PermissionDetailsOutput details(@Valid @RequestBody BaseIdInput input) {
        return null;
    }

    /**
     * 分页查询资源权限列表
     * @param input 查询参数
     * @return 资源权限分页列表
     */
    @ApiOperation("查询权限列表-分页查询")
    @PostMapping("/page")
    @Log(name = "查询权限列表-分页查询", type = OperateType.SELECT)
    public BasePageOutput<PermissionItemOutput> page(@Valid @RequestBody PermissionPageInput input) {
        return this.permissionService.getPage(input);
    }

    /**
     * 全部资源权限树
     * @return 全部资源权限树
     */
    @ApiOperation("全部资源权限树")
    @PostMapping("/tree")
    @Log(name = "查询全部资源权限树", type = OperateType.SELECT)
    public List<PermissionTreeOutput> tree() {
        return this.permissionService.getTree();
    }
}
