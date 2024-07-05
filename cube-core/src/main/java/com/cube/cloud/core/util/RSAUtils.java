package com.cube.cloud.core.util;

import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Created with IntelliJ IDEA.
 * RSA加解密
 * @author Long
 * @date 2023-05-31 14:46
 */
@Slf4j
public class RSAUtils {

    /**
     * 加密算法RSA
     */
    public static final String ALGORITHM_RSA = "RSA";

    /**
     * 签名算法
     */
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;


    /**
     * 生成密钥对
     * @return KeyPair
     * @throws Exception
     */
    public static KeyPair genKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance(ALGORITHM_RSA);
        generator.initialize(1024);
        return generator.generateKeyPair();

    }

    /**
     * 获取公钥
     * @return 公钥
     */
    public static String getPublicKey(RSAPublicKey publicKey) {
        return new BASE64Encoder().encode(publicKey.getEncoded());
    }

    /**
     *获取私钥
     * @return 私钥
     */
    public static String getPrivateKey(RSAPrivateKey privateKey) {
        return new BASE64Encoder().encode(privateKey.getEncoded());
    }

    /**
     * 私钥解密
     * @param data 已加密数据
     * @param privateKey 私钥(BASE64编码)
     * @return 解密后的数据
     */
    public static String decryptByPrivateKey(String data, String privateKey){
        try {
            byte[] encryptData = new BASE64Decoder().decodeBuffer(data);
            byte[] keyBytes = new BASE64Decoder().decodeBuffer(privateKey);
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
            Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, privateK);
            int inputLen = encryptData.length;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offSet = 0;
            byte[] cache;
            int i = 0;
            // 对数据分段解密
            while (inputLen - offSet > 0) {
                if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                    cache = cipher.doFinal(encryptData, offSet, MAX_DECRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(encryptData, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * MAX_DECRYPT_BLOCK;
            }
            out.close();
            return out.toString();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("=私钥解密失败=");
        }
        return null;
    }

    /**
     * 公钥解密
     * @param data 已加密数据
     * @param publicKey 公钥(BASE64编码)
     * @return 解密的的数据
     */
    public static String decryptByPublicKey(String data, String publicKey) {
        try {
            byte[] encryptData = new BASE64Decoder().decodeBuffer(data);
            byte[] keyBytes = new BASE64Decoder().decodeBuffer(publicKey);
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
            Key publicK = keyFactory.generatePublic(x509KeySpec);
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, publicK);
            int inputLen = encryptData.length;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offSet = 0;
            byte[] cache;
            int i = 0;
            // 对数据分段解密
            while (inputLen - offSet > 0) {
                if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                    cache = cipher.doFinal(encryptData, offSet, MAX_DECRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(encryptData, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * MAX_DECRYPT_BLOCK;
            }
            out.close();
            return out.toString();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("=公钥解密失败=");
        }
        return null;
    }

    /**
     * 私钥加密
     * @param data 需要加密的数据
     * @param privateKey 私钥(BASE64编码)
     * @return 见面后的数据
     */
    public static String encryptByPrivateKey(String data, String privateKey) {
        try {
            byte[] encryptData = data.getBytes();
            byte[] keyBytes = new BASE64Decoder().decodeBuffer(privateKey);
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
            Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, privateK);
            int inputLen = encryptData.length;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offSet = 0;
            byte[] cache;
            int i = 0;
            // 对数据分段加密
            while (inputLen - offSet > 0) {
                if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                    cache = cipher.doFinal(encryptData, offSet, MAX_ENCRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(encryptData, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * MAX_ENCRYPT_BLOCK;
            }
            byte[] encryptedData = out.toByteArray();
            out.close();
            return new BASE64Encoder().encode(encryptedData);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("=私钥加密失败=");
        }
        return null;
    }

    /**
     * 公钥加密
     * @param data 需要加密的数据
     * @param publicKey 公钥(BASE64编码)
     * @return 加密后的数据
     */
    public static String encryptByPublicKey(String data, String publicKey) {
        try {
            byte[] encryptData = data.getBytes();
            byte[] keyBytes = new BASE64Decoder().decodeBuffer(publicKey);
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
            Key publicK = keyFactory.generatePublic(x509KeySpec);
            // 对数据加密
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, publicK);
            int inputLen = encryptData.length;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offSet = 0;
            byte[] cache;
            int i = 0;
            // 对数据分段加密
            while (inputLen - offSet > 0) {
                if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                    cache = cipher.doFinal(encryptData, offSet, MAX_ENCRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(encryptData, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * MAX_ENCRYPT_BLOCK;
            }
            byte[] encryptedData = out.toByteArray();
            out.close();
            return new BASE64Encoder().encode(encryptedData);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("=公钥加密失败=");
        }
        return null;
    }

    /**
     * 用私钥对信息生成数字签名
     * @param data 已加密数据
     * @param privateKey 私钥(BASE64编码)
     * @return String
     * @throws Exception
     */
    public static String sign(byte[] data, String privateKey) throws Exception {
        byte[] keyBytes = new BASE64Decoder().decodeBuffer(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
        PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(privateK);
        signature.update(data);
        return new BASE64Encoder().encode(signature.sign());
    }

    /**
     * 校验数字签名
     * @param data 已加密数据
     * @param publicKey 公钥(BASE64编码)
     * @param sign 数字签名
     * @return boolean
     * @throws Exception
     */
    public static boolean verify(byte[] data, String publicKey, String sign) throws Exception {
        byte[] keyBytes = new BASE64Decoder().decodeBuffer(publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
        PublicKey publicK = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(publicK);
        signature.update(data);
        return signature.verify(new BASE64Decoder().decodeBuffer(sign));
    }
}
