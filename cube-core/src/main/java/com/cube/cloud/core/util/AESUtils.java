package com.cube.cloud.core.util;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created with IntelliJ IDEA.
 * 加密工具
 * @author Long
 * @date 2023-01-10 17:17
 */
public class AESUtils {


    /**
     * 加密算法AES
     */
    public static final String ALGORITHM_AES = "AES";

    /**
     * 签名算法
     */
    public static final String SIGNATURE_ALGORITHM = "AES/ECB/PKCS5Padding";


    static private KeyGenerator keyGen;

    static private Cipher cipher;

    static boolean isInited = false;

    private static void init() {
        try {
            /**
             * 为指定算法生成一个 KeyGenerator 对象。 此类提供（对称）密钥生成器的功能。 密钥生成器是使用此类的某个
             * getInstance 类方法构造的。 KeyGenerator 对象可重复使用，也就是说，在生成密钥后， 可以重复使用同一
             * KeyGenerator 对象来生成进一步的密钥。 生成密钥的方式有两种：与算法无关的方式，以及特定于算法的方式。
             * 两者之间的惟一不同是对象的初始化： 与算法无关的初始化 所有密钥生成器都具有密钥长度 和随机源 的概念。 此
             * KeyGenerator 类中有一个 init 方法，它可采用这两个通用概念的参数。 还有一个只带 keysize 参数的
             * init 方法， 它使用具有最高优先级的提供程序的 SecureRandom 实现作为随机源 （如果安装的提供程序都不提供
             * SecureRandom 实现，则使用系统提供的随机源）。 此 KeyGenerator 类还提供一个只带随机源参数的 inti
             * 方法。 因为调用上述与算法无关的 init 方法时未指定其他参数，
             * 所以由提供程序决定如何处理将与每个密钥相关的特定于算法的参数（如果有）。 特定于算法的初始化
             * 在已经存在特定于算法的参数集的情况下， 有两个具有 AlgorithmParameterSpec 参数的 init 方法。
             * 其中一个方法还有一个 SecureRandom 参数， 而另一个方法将已安装的高优先级提供程序的 SecureRandom
             * 实现用作随机源 （或者作为系统提供的随机源，如果安装的提供程序都不提供 SecureRandom 实现）。
             * 如果客户端没有显式地初始化 KeyGenerator（通过调用 init 方法）， 每个提供程序必须提供（和记录）默认初始化。
             */
            keyGen = KeyGenerator.getInstance(ALGORITHM_AES);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // 初始化此密钥生成器，使其具有确定的密钥长度。
        keyGen.init(128); // 128位的AES加密
        try {
            // 生成一个实现指定转换的 Cipher 对象。
            cipher = Cipher.getInstance(SIGNATURE_ALGORITHM);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
        // 标识已经初始化过了的字段
        isInited = true;
    }

    private static byte[] genKey() {
        if (!isInited) {
            init();
        }
        // 首先 生成一个密钥(SecretKey),
        // 然后,通过这个秘钥,返回基本编码格式的密钥，如果此密钥不支持编码，则返回 null。
        return keyGen.generateKey().getEncoded();
    }

    private static byte[] encrypt(byte[] content, byte[] keyBytes) {
        byte[] encryptedText = null;
        if (!isInited) {
            init();
        }
        // 类 SecretKeySpec 可以使用此类来根据一个字节数组构造一个 SecretKey，
        // 而无须通过一个（基于 provider类 SecretKeySpec 可以使用此类来根据一个字节数组构造一个 SecretKey，
        // 而无须通过一个（基于 provider的）SecretKeyFactory。
        // 此类仅对能表示为一个字节数组并且没有任何与之相关联的钥参数的原始密钥有用构造方法根据给定的字节数组构造一个密钥。
        // 此构造方法不检查给定的字节数组是否指定了一个算法的密钥。
        Key key = new SecretKeySpec(keyBytes, ALGORITHM_AES);
        try {
            // 用密钥初始化此 cipher。
            cipher.init(Cipher.ENCRYPT_MODE, key);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        try {
            // 按单部分操作加密或解密数据，或者结束一个多部分操作
            encryptedText = cipher.doFinal(content);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return encryptedText;
    }

    private static byte[] encrypt(String content, String password) {
        try {
            byte[] keyStr = getKey(password);
            SecretKeySpec key = new SecretKeySpec(keyStr, ALGORITHM_AES);
            Cipher cipher = Cipher.getInstance(SIGNATURE_ALGORITHM);
            byte[] byteContent = content.getBytes(StandardCharsets.UTF_8);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return cipher.doFinal(byteContent);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException
                | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static byte[] decrypt(byte[] content, String password) {
        try {
            byte[] keyStr = getKey(password);
            SecretKeySpec key = new SecretKeySpec(keyStr, ALGORITHM_AES);
            Cipher cipher = Cipher.getInstance(SIGNATURE_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            return cipher.doFinal(content);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException
                | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static byte[] getKey(String password) {
        byte[] rByte = null;
        if (password != null) {
            rByte = password.getBytes();
        } else {
            rByte = new byte[24];
        }
        return rByte;
    }

    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte[] buf) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     *
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    // 注意: 这里的password(秘钥必须是16位的)
    // private static final String keyBytes = "abcdefgabcdefg12";
    //
    /**
     * 加密
     */
    public static String encode(String content, String keyBytes) {
        // 加密之后的字节数组,转成16进制的字符串形式输出
        return parseByte2HexStr(encrypt(content, keyBytes));
    }

    /**
     * 解密
     */
    public static String decode(String content, String keyBytes) {
        // 解密之前,先将输入的字符串按照16进制转成二进制的字节数组,作为待解密的内容输入
        byte[] b = decrypt(parseHexStr2Byte(content), keyBytes);
        if (b == null) {
            return null;
        }
        return new String(b);
    }

    public static String MD5(String s) {
        char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        try {
            byte[] btInput = s.getBytes(StandardCharsets.UTF_8);
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
