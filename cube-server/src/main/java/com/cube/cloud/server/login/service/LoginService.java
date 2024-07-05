package com.cube.cloud.server.login.service;

import com.cube.cloud.core.application.model.BaseIdInput;
import com.cube.cloud.server.login.model.LoginInput;
import com.cube.cloud.server.login.model.LoginOutput;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-05-18 16:03
 */
public interface LoginService {

    /**
     * 账号密码登录
     * @param input 登录输入实体
     * @return LoginOutput
     */
    LoginOutput doLogin(LoginInput input, HttpServletRequest request);

    /**
     * 退出登录
     */
    void doLogout();

    /**
     * 踢人下线
     * @param input 用户id
     */
    void doKickOut(BaseIdInput input);
}
