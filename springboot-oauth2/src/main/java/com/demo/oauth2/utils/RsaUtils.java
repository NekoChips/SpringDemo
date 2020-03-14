package com.demo.oauth2.utils;

import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Yangjie.Chen
 * @description RSA公钥私钥 工具类
 * @date 2020/3/13
 */
public class RsaUtils {

    public static final String PUBLIC_KEY = "RSAPublicKey";

    public static final String PRIVATE_KEY = "RSAPrivateKey";

    private static final String ENCRYPT_ALGORITHM = "RSA";

    private static final int KEY_SIZE = 1024;

    // 用于存放随机生成的密钥对
    public static Map<String, String> keyMap = new HashMap<>(2);

    /**
     * 随机生成密钥对
     *
     * @return 密钥对 map
     */
    public static Map<String, String> getKeyPair() throws NoSuchAlgorithmException {
        // 基于 RSA 算法生成 keyPairGenerator ，用于生成密钥对
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ENCRYPT_ALGORITHM);
        // 使用随机数初始化 keyPairGenerator
        keyPairGenerator.initialize(KEY_SIZE, new SecureRandom());
        // 生成密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        keyMap.put(PUBLIC_KEY, Base64Utils.encodeToString(publicKey.getEncoded()));
        keyMap.put(PRIVATE_KEY, Base64Utils.encodeToString(privateKey.getEncoded()));

        return RsaUtils.keyMap;
    }

    /**
     * 使用 RSA 私钥解密
     * @param str 加密字符串
     * @param privateKeyStr 加密后的私钥字符串
     * @return 解密字符串
     * @throws Exception 异常
     */
    public static String decryptByPrivate(String str, String privateKeyStr) throws Exception {
        // 将加密字符串进行 base64 解码
        byte[] strByte = Base64Utils.decodeFromString(str);
        // 将 私钥 解码
        byte[] privateByte = Base64Utils.decodeFromString(privateKeyStr);

        RSAPrivateKey privateKey =
                (RSAPrivateKey) KeyFactory.getInstance(ENCRYPT_ALGORITHM).generatePrivate(new PKCS8EncodedKeySpec(privateByte));

        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        return new String(cipher.doFinal(strByte));
    }
}
