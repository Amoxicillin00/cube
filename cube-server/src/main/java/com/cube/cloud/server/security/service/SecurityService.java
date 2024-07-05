package com.cube.cloud.server.security.service;

import com.cube.cloud.server.user.entity.User;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-06-01 15:17
 */
public interface SecurityService {

    /**
     * 获取公钥
     * @return String
     */
    String getPublicKey();

    /**
     * 获取私钥
     * @return String
     */
    String getPrivateKey();

    /**
     * 私钥解密
     * @param data 已加密数据
     * @return String
     */
    String decryptByPrivateKey(String data);

    /**
     * 公钥加密
     * @param data 需要加密的数据
     * @return String
     */
    String encryptByPublicKey(String data);

    /**
     * md5编码
     * @param user 用户信息
     * @param password RSA加密后的密码
     * @return 编译后的编码
     */
    String encodeByMD5(User user, String password);

    /**
     * 检查密码
     * @param user 用户信息
     * @param password RSA加密后的密码
     * @return boolean
     */
    boolean matchesByMD5(User user, String password);



}
