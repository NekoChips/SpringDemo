package com.demo.oauth2.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

/**
 * @author Yangjie.Chen
 * @description 自定义登录成功处理器
 * @date 2020/3/12
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(MyAuthenticationSuccessHandler.class);

    private static final String HEADER_PREFIX = "Basic ";

    @Autowired
    private ObjectMapper mapper;

    // 注入客户端查询接口
    @Autowired
    private ClientDetailsService clientDetailsService;

    // 注入认证服务token接口
    @Autowired
    private AuthorizationServerTokenServices authorizationServerTokenServices;

    // 注入密码加密、校验器
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        /**
         * 登录成功后需要生成令牌并返回
         * 从 oauth2-demo 中了解到，生成令牌的方式有四种，但是每一种都需要客户端的 clientId 与 clientSecret
         * 将 {clientId:clientSecret} 进行 base64 加密后放入请求头中进行令牌的生成。
         *  所以下面的代码分解为以下步骤：
         *      1. 从请求头中获取 clientId；
         *      2. 调用 ClientDetailsService 查询对应的 clientSecret；
         *      3. 校验 clientId 和 clientSecret 的正确性；
         *      4. 使用 TokenRequest 构造器生成一个 获取 token 的请求 tokenRequest；
         *      5. 调用 TokenRequest.createOAuth2Request() 方法创建对应的 OAuth2Request 对象
         *      6. 构建 OAuth2Authentication OAuth认证对象
         *      7. 生成 OAuth2AccessToken 并返回
         */

        // 1. 从请求头中获取 clientId
        String header = request.getHeader("Authorization");
        if (StringUtils.isEmpty(header) || !header.startsWith("Basic ")) {
            // OAuth2 的规定 Authorization 的值必须为以 "Basic " 开头后面接上{clientId:clientSecret}的 base64 转码
            throw new UnapprovedClientAuthenticationException("no client detail in request header");
        }
        // 对 Authorization 的值进行 base64 解码
        header = header.substring(HEADER_PREFIX.length());
        // clientStr[0] 为 clientId, clientStrs[1] 为 clientSecret
        String[] clientStr = decodeHeader(header);

        // 2. 调用 ClientDetailsService 查询对应的 clientSecret
        String clientId = clientStr[0];
        String clientSecret = clientStr[1];
        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
        // 3. 校验 clientId 和 clientSecret 的正确性
        if (clientDetails == null) {
            logger.error("no matched client detail, clientId: {}", clientId);
            throw new UnapprovedClientAuthenticationException("no exist client details , clientId: " + clientId);
        } else if (!passwordEncoder.matches(clientSecret, clientDetails.getClientSecret())) {
            logger.error("client secret was wrong, clientSecret: {}", clientSecret);
            throw new UnapprovedClientAuthenticationException("client secret not matched");
        }

        // 4. 使用 TokenRequest 构造器生成一个 获取 token 的请求 tokenRequest
        TokenRequest tokenRequest =
                new TokenRequest(new HashMap<>(), clientId, clientDetails.getScope(), "custom");
        // 5. 调用 TokenRequest.createOAuth2Request() 方法创建对应的 OAuth2Request 对象
        OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
        // 6. 构建 OAuth2Authentication OAuth认证对象
        OAuth2Authentication auth2Authentication =
                new OAuth2Authentication(oAuth2Request, authentication);
        // 7. 生成 OAuth2AccessToken 并返回
        OAuth2AccessToken accessToken
                = authorizationServerTokenServices.createAccessToken(auth2Authentication);
        logger.info("create access_token success");

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(mapper.writeValueAsString(accessToken));
    }

    /**
     * base64 解码
     *
     * @param header 请求头部信息
     * @return
     */
    protected String[] decodeHeader(String header) {
        byte[] base64Header = header.getBytes(StandardCharsets.UTF_8);
        byte[] decode;

        decode = Base64Utils.decode(base64Header);
        String token = new String(decode, StandardCharsets.UTF_8);

        String[] result = token.split(":");
        if (result.length < 2) {
            throw new BadCredentialsException("Invalid basic authentication token");
        }
        return result;
    }
}
