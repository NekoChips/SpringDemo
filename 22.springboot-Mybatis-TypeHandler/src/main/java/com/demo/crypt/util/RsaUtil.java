package com.demo.crypt.util;

import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import javax.validation.constraints.NotNull;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @author NekoChips
 * @description RSA公钥私钥 工具类
 * @date 2020/4/2
 */
public class RsaUtil {

    public static final String PUBLIC_KEY = "RSAPublicKey";

    public static final String PRIVATE_KEY = "RSAPrivateKey";

    private static final String ENCRYPT_ALGORITHM = "RSA";

    private static final int KEY_SIZE = 2048;

    /**
     * 用于存放随机生成的密钥对
     */
    public static Map<String, String> keyMap = new HashMap<>(2);

    public static RSAPublicKey publicKey;

    public static RSAPrivateKey privateKey;

    /**
     * 初始化密钥对
     *
     * @param keyPair 密钥对
     */
    public static void initKeyPair(KeyPair keyPair) {
        publicKey = (RSAPublicKey) keyPair.getPublic();
        privateKey = (RSAPrivateKey) keyPair.getPrivate();
    }

    /**
     * 随机生成密钥对
     *
     * @return 密钥对
     */
    public static KeyPair getKeyPair() throws NoSuchAlgorithmException {
        KeyPair keyPair;
        if (publicKey == null || privateKey == null) {
            // 基于 RSA 算法生成 keyPairGenerator ，用于生成密钥对
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ENCRYPT_ALGORITHM);
            // 使用随机数初始化 keyPairGenerator
            keyPairGenerator.initialize(KEY_SIZE, new SecureRandom());
            // 生成密钥对
            keyPair = keyPairGenerator.generateKeyPair();
            initKeyPair(keyPair);
        } else {
            keyPair = new KeyPair(publicKey, privateKey);
        }
        // 编码后的 密钥对 添加至map
        keyMap.put(PUBLIC_KEY, Base64Utils.encodeToString(publicKey.getEncoded()));
        keyMap.put(PRIVATE_KEY, Base64Utils.encodeToString(privateKey.getEncoded()));

        return keyPair;
    }

    /**
     * 转换公钥
     *
     * @param publicKeyStr 公钥编码字符串
     * @return 公钥
     * @throws Exception 异常
     */
    public static PublicKey str2PublicKey(String publicKeyStr) throws Exception {
        byte[] keyBytes = Base64Utils.decodeFromString(publicKeyStr);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ENCRYPT_ALGORITHM);
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * 转换私钥
     *
     * @param privateKeyStr 私钥编码字符串
     * @return 私钥
     * @throws Exception 异常
     */
    public static PrivateKey str2PrivateKey(String privateKeyStr) throws Exception {
        byte[] keyBytes = Base64Utils.decodeFromString(privateKeyStr);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ENCRYPT_ALGORITHM);
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * 使用 RSA 公钥加密
     * @param str 加密内容
     * @param publicKeyStr 公钥字符串
     * @return 加密后的内容
     */
    public static String encrypt(@NotNull String str, String publicKeyStr) throws Exception{
        RSAPublicKey publicKey = (RSAPublicKey) str2PublicKey(publicKeyStr);
        return encrypt(str, publicKey);
    }

    /**
     * 使用 RSA 公钥加密
     * @param str 加密内容
     * @param publicKey 公钥
     * @return 加密后的内容
     */
    public static String encrypt(@NotNull String str, RSAPublicKey publicKey) throws Exception{
        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return Base64Utils.encodeToString(cipher.doFinal(str.getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * 使用 RSA 私钥解密
     *
     * @param str           加密字符串
     * @param privateKeyStr 加密后的私钥字符串
     * @return 解密字符串
     * @throws Exception 异常
     */
    public static String decrypt(@NotNull String str, String privateKeyStr) throws Exception {
        // 将 私钥 解码
        byte[] privateByte = Base64Utils.decodeFromString(privateKeyStr);
        RSAPrivateKey privateKey =
                (RSAPrivateKey) KeyFactory.getInstance(ENCRYPT_ALGORITHM).generatePrivate(new PKCS8EncodedKeySpec(privateByte));

        return decrypt(str, privateKey);
    }

    /**
     * 使用 RSA 私钥解密
     * @param str 加密后的内容
     * @param privateKey 私钥
     * @return 原加密内容
     */
    public static String decrypt(@NotNull String str, RSAPrivateKey privateKey) throws Exception {
        byte[] bytes = Base64Utils.decodeFromString(str);
        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(cipher.doFinal(bytes));
    }
}
