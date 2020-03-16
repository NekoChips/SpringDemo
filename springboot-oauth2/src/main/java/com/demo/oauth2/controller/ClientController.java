package com.demo.oauth2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Yangjie.Chen
 * @description 客户端 controller
 * @date 2020/3/16
 */
@RestController
@RequestMapping("client")
public class ClientController {
    @Resource
    private JdbcClientDetailsService jdbcClientDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("addDetails")
    public void addDetails(HttpServletRequest request) {
        // 必填项
        String clientId = request.getParameter("clientId");
        String clientSecret = request.getParameter("clientSecret");
        String[] scopes = request.getParameter("scopes").split(",");
        String[] grantTypes = request.getParameter("grantTypes").split(",");
        // 非必填项
        String redirectUri = request.getParameter("redirectUri");
        Set<String> redirectUris =
                StringUtils.isEmpty(redirectUri) ?
                        null : Arrays.stream(request.getParameter(
                        "redirectUri").split(","))
                        .collect(Collectors.toSet());


        BaseClientDetails clientDetails = new BaseClientDetails();
        clientDetails.setClientId(clientId);
        clientDetails.setClientSecret(passwordEncoder.encode(clientSecret));
        clientDetails.setScope(Arrays.asList(scopes));
        clientDetails.setAuthorizedGrantTypes(Arrays.asList(grantTypes));
        clientDetails.setRegisteredRedirectUri(redirectUris);
        clientDetails.setAccessTokenValiditySeconds(3600);
        clientDetails.setRefreshTokenValiditySeconds(86400);
        clientDetails.setAutoApproveScopes(Collections.singletonList("true"));

        jdbcClientDetailsService.addClientDetails(clientDetails);
    }

}
