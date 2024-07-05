package com.cube.cloud.server.security.service.impl;

import cn.hutool.core.util.StrUtil;
import com.cube.cloud.core.exception.ExceptionUtils;
import com.cube.cloud.core.redis.RedisUtils;
import com.cube.cloud.core.util.MD5Utils;
import com.cube.cloud.core.util.RSAUtils;
import com.cube.cloud.server.security.service.SecurityService;
import com.cube.cloud.server.user.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Long
 * @date 2023-06-01 15:18
 */
@Service
public class SecurityServiceImpl implements SecurityService {

    private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);

    /**
     * 公钥的key
     */
    public static final String RSA_PUBLIC_KEY = "RSA:PUBLIC_KEY";

    /**
     * 私钥的key
     */
    public static final String RSA_PRIVATE_KEY = "RSA:PRIVATE_KEY";

    private Charset charset = StandardCharsets.UTF_8;

    @Resource
    private RedisUtils redisUtils;



    @Override
    public String getPublicKey() {
        // 如果缓存中不存在密钥对则重新生成新的密钥对
        if (Boolean.FALSE.equals(this.redisUtils.hasKey(RSA_PUBLIC_KEY))) {
            this.genKeyPair();
        }
        return this.redisUtils.get(RSA_PUBLIC_KEY);
    }

    @Override
    public String getPrivateKey() {
        // 校验密钥
        ExceptionUtils.check(!this.redisUtils.hasKey(RSA_PRIVATE_KEY), "密钥已失效");
        return this.redisUtils.get(RSA_PRIVATE_KEY);
    }

    @Override
    public String decryptByPrivateKey(String data) {
        return RSAUtils.decryptByPrivateKey(data, this.getPrivateKey());
    }

    @Override
    public String encryptByPublicKey(String data) {
        return RSAUtils.encryptByPublicKey(data, this.getPublicKey());
    }

    @Override
    public String encodeByMD5(User user, String password) {
        String source = String.format("%s_%s_%s", user.getId(), password, StrUtil.reverse(password));
        return MD5Utils.md5(source, this.getCharset());
    }

    @Override
    public boolean matchesByMD5(User user, String password) {
        return this.encodeByMD5(user, password).equals(user.getPassword());
    }


    private void genKeyPair() {
        try {
            KeyPair keyPair = RSAUtils.genKeyPair();
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
            // 存入缓存
            this.redisUtils.set(RSA_PUBLIC_KEY, RSAUtils.getPublicKey(publicKey));
            this.redisUtils.set(RSA_PRIVATE_KEY, RSAUtils.getPrivateKey(privateKey));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("=密钥对生成失败=");
        }
    }

    public Charset getCharset() {
        return charset;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }
}
