package com.cube.cloud.server.user.controller;

import com.cube.cloud.core.application.model.BaseIdInput;
import com.cube.cloud.core.application.model.BasePageOutput;
import com.cube.cloud.core.application.model.BaseStatusInput;
import com.cube.cloud.core.log.OperateType;
import com.cube.cloud.core.log.annotation.Log;
import com.cube.cloud.server.user.model.*;
import com.cube.cloud.server.user.service.UserService;
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
 * 用户服务
 * @author Long
 * @date 2023-04-06 14:52
 */
@RestController
@RequestMapping("/sys/user")
@Api(tags = "用户服务")
public class UserController {

    @Autowired
    private UserService userService;

    
    /**
     * 添加用户
     * @param input 输入参数
     */
    @ApiOperation("添加用户")
    @PostMapping("/add")
    @Log(name = "添加用户", type = OperateType.CREATE)
    public void add(@Valid @RequestBody UserAddInput input) {
        this.userService.add(input);
    }

    /**
     * 删除用户
     * @param input 输入参数
     */
    @ApiOperation("删除用户")
    @PostMapping("/delete")
    @Log(name = "删除用户", type = OperateType.DELETE)
    public void delete(@Valid @RequestBody BaseIdInput input) {
        this.userService.delete(input);
    }

    /**
     * 修改用户信息
     * @param input 输入参数
     */
    @ApiOperation("修改用户信息")
    @PostMapping("/update")
    @Log(name = "修改用户信息", type = OperateType.UPDATE)
    public void update(@Valid @RequestBody UserModifyInput input) {
        this.userService.update(input);
    }

    /**
     * 用户详情
     * @param input 输入参数
     * @return 用户详情信息
     */
    @ApiOperation("用户详情")
    @PostMapping("/details")
    @Log(name = "查询用户详情", type = OperateType.SELECT)
    public UserDetailsOutput details(@Valid @RequestBody BaseIdInput input) {
        return this.userService.getDetails(input);
    }

    /**
     * 用户列表
     * @return 用户信息列表
     */
    @ApiOperation("用户列表")
    @PostMapping("/list")
    @Log(name = "查询用户列表", type = OperateType.SELECT)
    public List<UserItemOutput> list() {
        return this.userService.getList();
    }

    /**
     * 用户列表-分页查询
     * @param input 输入参数
     * @return 用户信息列表
     */
    @ApiOperation("用户列表-分页查询")
    @PostMapping("/page")
    @Log(name = "查询用户列表-分页查询", type = OperateType.SELECT)
    public BasePageOutput<UserItemOutput> page(@Valid @RequestBody UserPageInput input) {
        return this.userService.getPage(input);
    }

    /**
     * 更新密码
     * @param input 输入参数
     */
    @ApiOperation("更新密码")
    @PostMapping("/update/password")
    @Log(name = "修改账号密码", type = OperateType.UPDATE)
    public void updatePassword(@Valid @RequestBody UpdatePasswordInput input) {
        this.userService.updatePassword(input);
    }

    /**
     * 修改状态(0 : 禁用、1 : 启用)
     * @param input 输入参数
     */
    @ApiOperation("修改状态(0 : 禁用、1 : 启用)")
    @PostMapping("/update/status")
    @Log(name = "修改用户状态", type = OperateType.UPDATE)
    public void updateStatus(@Valid @RequestBody BaseStatusInput input) {
        this.userService.updateStatus(input);
    }

    /**
     * 校验电话号码
     * @param input 输入参数
     * @return 电话号码是否存在
     */
    @ApiOperation("校验电话号码")
    @PostMapping("/check/phone")
    public Boolean checkPhoneNumber(@Valid @RequestBody CheckPhoneInput input) {
        return this.userService.checkPhoneNumber(input.getPhoneNumber(),
                input.getOrganizationId(), input.getId());
    }

    /**
     * 获取角色用户列表
     * @param input 输入参数
     * @return 角色用户列表
     */
    @ApiOperation("获取角色用户列表")
    @PostMapping("/list/by/role/id")
    @Log(name = "查询角色用户列表", type = OperateType.SELECT)
    public List<RoleUserOutput> getListByRoleId(@Valid @RequestBody BaseIdInput input) {
        return this.userService.getListByRoleId(input.getId());
    }



    //--------------------------------------用户自己操作接口（需要通过session获取当前用户信息）--------------------------------------------------//

    /**
     * 获取用户自己的详情信息
     */

    /**
     * 用户修改密码
     */

    /**
     * 用户修改自己信息
     */
}
