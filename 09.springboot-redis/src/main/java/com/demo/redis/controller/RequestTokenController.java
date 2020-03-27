package com.demo.redis.controller;

import com.demo.redis.service.IRequestTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author NekoChips
 * @description RequestTokenController
 * @date 2020/3/27
 */
@RestController
public class RequestTokenController {

    @Autowired
    private IRequestTokenService requestTokenService;

    @GetMapping("requestToken")
    public HttpEntity<?> createToken() {
        String token = requestTokenService.createToken();
        return ResponseEntity.ok(token);
    }
}
