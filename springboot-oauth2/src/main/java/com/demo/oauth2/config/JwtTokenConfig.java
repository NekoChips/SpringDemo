package com.demo.oauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.security.interfaces.RSAPublicKey;

/**
 * @author Yangjie.Chen
 * @description JWT 令牌配置
 * @date 2020/3/13
 */
@Configuration
public class JwtTokenConfig {

    private static final String PUBLIC_KEY = "test_public";

    // jwtToken 转换器
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
        // 设置 jwtToken 签名密钥
        tokenConverter.setSigningKey(PUBLIC_KEY);
        return tokenConverter;
    }

    // jwtToken
    @Bean
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    /**
     * 将字符串转换为 RSAPublicKey
     * @param publicKey 公钥字符串
     * @return
     */
    private RSAPublicKey transPublicKey(String publicKey) {
        return null;
    }

}
