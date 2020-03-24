package com.demo.oauth2.config;

import com.demo.oauth2.utils.RsaUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;

/**
 * @author Yangjie.Chen
 * @description JWT 令牌配置
 * @date 2020/3/13
 */
@Configuration
public class JwtTokenConfig {

    // 对称加密的公共密钥
    private static final String PUBLIC_KEY = "test_public";

    // jwtToken 转换器
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
        // 设置 jwtToken 签名密钥
//        tokenConverter.setSigningKey(PUBLIC_KEY);
        try {
            KeyPair keyPair = RsaUtils.getKeyPair();
            // 配置 RSA 校验器
            tokenConverter.setVerifier(new RsaVerifier((RSAPublicKey) keyPair.getPublic()));
            tokenConverter.setKeyPair(keyPair);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return tokenConverter;
    }

    // jwtToken
    @Bean
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    // 注册 JwtTokenEnhancer
    @Bean
    public TokenEnhancer jwtTokenEnhancer() {
        return new JwtTokenEnhancer();
    }

}
