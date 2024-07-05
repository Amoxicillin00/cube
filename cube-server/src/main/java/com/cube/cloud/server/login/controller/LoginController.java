package com.cube.cloud.server.login.controller;

import com.cube.cloud.core.application.model.BaseIdInput;
import com.cube.cloud.core.log.OperateType;
import com.cube.cloud.core.log.annotation.Log;
import com.cube.cloud.server.login.model.LoginInput;
import com.cube.cloud.server.login.model.LoginOutput;
import com.cube.cloud.server.login.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created with IntelliJ IDEA.
 * 登录服务
 * @author Long
 * @date 2023-05-18 16:04
 */
@RestController
@RequestMapping("/login")
@Api(tags = "登录服务")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @ApiOperation("账号密码登录")
    @Log(name = "账号密码登录", type = OperateType.LOGIN)
    @PostMapping("/doLogin")
    public LoginOutput doLogin(@Valid @RequestBody LoginInput input, HttpServletRequest request) {
        return this.loginService.doLogin(input, request);
    }

    @ApiOperation("退出登录")
    @Log(name = "退出登录", type = OperateType.LOGOUT)
    @PostMapping("/doLogout")
    public void doLogout() {
        this.loginService.doLogout();
    }

    @ApiOperation("踢人下线")
    @Log(name = "踢人下线", type = OperateType.LOGOUT)
    @PostMapping("/doKickOut")
    public void doKickOut(@Valid @RequestBody BaseIdInput input) {
        this.loginService.doKickOut(input);
    }
}
