package com.demo.oauth2.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Yangjie.Chen
 * @description 测试 controller
 * @date 2020/3/13
 */
@RestController
public class TestController {

    @GetMapping("test/authInfo")
    public Object authInfo(Authentication authentication) {
        return authentication;
    }

    @GetMapping("decodeJWT")
    public HttpEntity<?> decode(HttpServletRequest request, @AuthenticationPrincipal Authentication authentication) {
        String header = request.getHeader("Authorization");
        String jwtToken = header.substring("bearer ".length());
        Jwt decodedJWT = JwtHelper.decode(jwtToken);
//        decodedJWT.verifySignature(new RsaVerifier(RsaUtils.PUBLIC_KEY));

        return ResponseEntity.ok(decodedJWT);
    }
}
