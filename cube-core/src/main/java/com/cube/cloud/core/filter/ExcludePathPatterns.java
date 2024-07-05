package com.cube.cloud.core.filter;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * 权限开发地址（无需登录）
 * @author Long
 * @date 2023-05-18 9:54
 */
public class ExcludePathPatterns {

    public static final Set<String> EXCLUDE_PATH_PATTERN_SET = new HashSet<String>() {

        private static final long serialVersionUID = 2505516695711799605L;

        {
            add("/**/**.html");
            add("/**/**.js");
            add("/**/**.css");
            add("/**/swagger-resources/**");
            add("/**/v2/api-docs");
            add("/**/doc.html");
            add("/**/webjars/**");
            add("/**/error");
            add("/**/favicon.ico");
            add("/login/doLogin");                                      // 账号密码登录
            add("/security/rsa/public");                               // 获取RSA公钥
            add("/security/rsa/public/encrypt");
            add("/security/rsa/private/decrypt");
        }
    };

    public static String[] excludePathPatterns(String... excludePathPatterns) {
        if (excludePathPatterns != null && excludePathPatterns.length > 0) {
            Collections.addAll(EXCLUDE_PATH_PATTERN_SET, excludePathPatterns);
        }
        return EXCLUDE_PATH_PATTERN_SET.toArray(new String[0]);
    }
}
