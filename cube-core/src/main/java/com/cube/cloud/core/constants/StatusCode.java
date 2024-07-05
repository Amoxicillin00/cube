package com.cube.cloud.core.constants;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-04-13 15:30
 */
public class StatusCode {

    /**
     * 成功
     */
    public static final int SUCCESS = 200;

    /**
     * 默认异常
     */
    public static final int ERROR = 400;

    /**
     * 系统异常
     */
    public static final int SYSTEM_ERROR = 500;

    /**
     * 不支持异常
     */
    public static final int SYSTEM_ERROR_UN_SUPPORT = SYSTEM_ERROR + 1;

    /**
     * 格式异常
     */
    public static final int SYSTEM_ERROR_FORMAT = SYSTEM_ERROR + 2;

    /**
     * 签名异常
     */
    public static final int SYSTEM_ERROR_SIGN = SYSTEM_ERROR + 3;

    /**
     * 无效转换
     */
    public static final int SYSTEM_ERROR_INVALID_CAST = SYSTEM_ERROR + 4;

    /**
     * 参数不正确
     */
    public static final int SYSTEM_ERROR_PARAMETER = SYSTEM_ERROR + 5;

    /**
     * 未登录
     */
    public static final int SYSTEM_ERROR_NOT_LOGIN = SYSTEM_ERROR + 6;

    /**
     * 需要图形验证码登录
     */
    public static final int SYSTEM_ERROR_NEED_IMG_LOGIN = SYSTEM_ERROR + 7;

    /**
     * 参数为null
     */
    public static final int SYSTEM_ERROR_PARAMETER_NULL = SYSTEM_ERROR + 8;

    /**
     * 参数为空
     */
    public static final int SYSTEM_ERROR_PARAMETER_BLANK = SYSTEM_ERROR + 9;

    /**
     * 参数超过范围
     */
    public static final int SYSTEM_ERROR_PARAMETER_OVERFLOW = SYSTEM_ERROR + 10;

    /**
     * 验证异常
     */
    public static final int SYSTEM_ERROR_VALIDATION = SYSTEM_ERROR + 11;

    /**
     * 配置异常
     */
    public static final int SYSTEM_ERROR_CONFIGURE = SYSTEM_ERROR + 12;

    /**
     * 数据库异常
     */
    public static final int SYSTEM_ERROR_DATABASE = SYSTEM_ERROR + 13;

    /**
     * 网络异常
     */
    public static final int SYSTEM_ERROR_NETWORK = SYSTEM_ERROR + 14;



    /**
     *应用异常
     */
    public static final int APPLICATION_ERROR = 2500;

    /**
     *未登录异常
     */
    public static final int APPLICATION_ERROR_NOT_LOGIN = APPLICATION_ERROR + 1;

    /**
     * 权限异常
     */
    public static final int APPLICATION_ERROR_AUTHORIZATION = APPLICATION_ERROR + 2;

    /**
     * 无效账户
     */
    public static final int APPLICATION_ERROR_ACCOUNT_INVALID = APPLICATION_ERROR + 3;

    /**
     * 账户状态异常
     */
    public static final int APPLICATION_ERROR_ACCOUNT_STATUS = APPLICATION_ERROR + 4;

    /**
     * 账户认证异常
     */
    public static final int APPLICATION_ERROR_ACCOUNT_CREDENTIALS = APPLICATION_ERROR + 5;

    /**
     * 票据无效
     */
    public static final int APPLICATION_ERROR_TOKEN_INVALID = APPLICATION_ERROR + 6;
}
