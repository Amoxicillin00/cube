package com.cube.cloud.server.role.controller;

import com.cube.cloud.core.application.model.BaseIdInput;
import com.cube.cloud.core.application.model.BasePageOutput;
import com.cube.cloud.core.log.OperateType;
import com.cube.cloud.core.log.annotation.Log;
import com.cube.cloud.server.role.model.*;
import com.cube.cloud.server.role.service.RoleService;
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
 * 角色服务
 * @author Long
 * @date 2023-01-04 11:01
 */
@RestController
@RequestMapping("/sys/role")
@Api(tags = "角色服务")
public class RoleController {

    @Autowired
    private RoleService roleService;



    /**
     * 添加角色
     * @param input 输入参数
     */
    @ApiOperation("添加角色")
    @PostMapping("/add")
    @Log(name = "添加角色", type = OperateType.CREATE)
    public void add(@Valid @RequestBody RoleAddInput input) {
        this.roleService.add(input);
    }

    /**
     * 删除用户
     * @param input 输入参数
     */
    @ApiOperation("删除用户")
    @PostMapping("/delete")
    @Log(name = "删除用户", type = OperateType.DELETE)
    public void delete(@Valid @RequestBody BaseIdInput input) {
        this.roleService.delete(input);
    }

    /**
     * 修改角色信息
     * @param input 输入参数
     */
    @ApiOperation("修改角色信息")
    @PostMapping("/update")
    @Log(name = "修改角色信息", type = OperateType.UPDATE)
    public void update(@Valid @RequestBody RoleModifyInput input) {
        this.roleService.update(input);
    }

    /**
     * 角色详情
     * @param input 输入参数
     * @return 角色详情信息
     */
    @ApiOperation("查询角色详情")
    @PostMapping("/details")
    @Log(name = "查询角色详情", type = OperateType.SELECT)
    public RoleDetailsOutput details(@Valid @RequestBody BaseIdInput input) {
        return this.roleService.getDetails(input);
    }

    /**
     * 角色列表
     * @param input 输入参数
     * @return 角色信息列表
     */
    @ApiOperation("查询角色列表")
    @PostMapping("/list")
    @Log(name = "查询角色列表", type = OperateType.SELECT)
    public List<RoleItemOutput> list(@Valid @RequestBody RoleListInput input) {
        return this.roleService.getList(input);
    }

    /**
     * 角色列表-分页查询
     * @param input 输入参数
     * @return 角色信息列表
     */
    @ApiOperation("查询角色列表-分页查询")
    @PostMapping("/page")
    @Log(name = "查询角色列表-分页查询", type = OperateType.SELECT)
    public BasePageOutput<RoleItemOutput> page(@Valid @RequestBody RolePageInput input) {
        return this.roleService.getPage(input);
    }

    /**
     * 角色授权
     * @param input 输入参数
     */
    @ApiOperation("角色授权")
    @PostMapping("/authorize")
    @Log(name = "角色授权", type = OperateType.OTHER)
    public void authorize(@Valid @RequestBody AuthorizationInput input) {
        this.roleService.authorize(input);
    }

}
