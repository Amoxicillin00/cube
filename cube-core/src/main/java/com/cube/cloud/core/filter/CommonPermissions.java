package com.cube.cloud.core.filter;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * 公共权限（需要登录）
 * @author Long
 * @date 2023-05-18 10:24
 */
public class CommonPermissions {
    public static final Set<String> COMMON_PERMISSION_SET = new HashSet<String>() {

        private static final long serialVersionUID = -2552143344010198290L;

        {
            add("/file/upload");                                        // 文件上传
            add("/login/signOut");                                    // 退出登录
        }
    };

    public static List<String> commonPermissions(String... commonPermissions) {
        if (commonPermissions != null && commonPermissions.length > 0) {
            Collections.addAll(COMMON_PERMISSION_SET, commonPermissions);
        }
        return new ArrayList<>(COMMON_PERMISSION_SET);
    }
}
