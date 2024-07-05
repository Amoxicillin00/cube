package com.cube.cloud.core.constants;

/**
 * Created with IntelliJ IDEA.
 * 常量
 * @author Long
 * @date 2023-01-09 11:07
 */
public class Constants {

    /**
     * 扫描路径
     */
    public static final String SCAN_BASE_PACKAGE = "com.cube.cloud";

    /**
     * mapper扫描路径
     */
    public static final String SCAN_MAPPER = SCAN_BASE_PACKAGE + ".server.*.mapper";

    /**
     * 日志mapper扫描路径
     */
    public static final String SCAN_CORE_MAPPER = SCAN_BASE_PACKAGE + ".core.*.mapper";

    /**
     * knife4j扫描路径
     */
    public static final String SCAN_SWAGGER_PACKAGE = SCAN_BASE_PACKAGE + ".server";

    /**
     * 超级管理员用户ID
     */
    public static final Long SUPER_ADMIN = 10001L;

    /**
     * 超级管理员角色ID
     */
    public static final Long SUPER_ROLE = 1360570778115710001L;

    /**
     * 超级管理员
     */
    public static final String SUPER_ADMIN_NAME = "超级管理员";

    /**
     * 登录失败次数
     */
    public static final String ERROR_COUNT = "Authorization:login:error-count:";

}
