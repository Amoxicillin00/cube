package com.cube.cloud.core.web.context;

import cn.dev33.satoken.SaManager;
import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import com.cube.cloud.core.application.dto.CurrentUser;
import com.cube.cloud.core.constants.Constants;
import com.cube.cloud.core.exception.ExceptionUtils;

import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 * 当前上下文
 * @author Long
 * @date 2023-06-14 17:42
 */
public class BaseContext {

    private BaseContext() {

    }

    /**
     * web上下文有效，并且当前会话已经登录
     * @return 是否已登录
     */
    public static boolean isLogin() {
        return SaManager.getSaTokenContext().isValid() && StpUtil.isLogin();
    }

    /**
     * web上下文有效，并且当前会话是超级管理员
     * @return 是否为超级管理员
     */
    public static boolean isSuperAdmin() {
        ExceptionUtils.check(!isLogin(), "用户未登录");
        return Objects.equals(Constants.SUPER_ADMIN, StpUtil.getLoginIdAsLong());
    }

    /**
     * 判断是否为超级管理员
     * @param loginId 前登录的用户用户id
     * @return 是否为超级管理员
     */
    public static boolean isSuperAdmin(Long loginId) {
        ExceptionUtils.check(!isLogin(), "用户未登录");
        return Objects.equals(Constants.SUPER_ADMIN, loginId);
    }

    /**
     * 获取当前登录用户信息，获取不到则抛出token无效异常
     * @return 当前登录用户信息
     */
    public static CurrentUser getCurrentUser() {
        ExceptionUtils.check(!isLogin(), "用户未登录");
        return StpUtil.getSession().getModel(SaSession.USER, CurrentUser.class);
    }
}
