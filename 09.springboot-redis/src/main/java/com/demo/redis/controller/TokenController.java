package com.demo.redis.controller;

import com.demo.redis.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * token controller
 */
@RestController
@RequestMapping("token")
public class TokenController {
    @Autowired
    private TokenService tokenService;

    @RequestMapping("create")
    public String token() {
        String token = tokenService.createToken();
        return token;
    }

}
