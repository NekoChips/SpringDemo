package com.demo.oauth2.config;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yangjie.Chen
 * @description Jwt token 增强器
 * @date 2020/3/15
 */
public class JwtTokenEnhancer implements TokenEnhancer {

    // 实际开发中这里可以是一个 密钥
    private static final String PRIVATE_SECRET = "private_secret";

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String, Object> enhanceMap = new HashMap<>();
        // 增强的内容
        enhanceMap.put("secret", PRIVATE_SECRET);
        ((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(enhanceMap);
        return accessToken;
    }
}
