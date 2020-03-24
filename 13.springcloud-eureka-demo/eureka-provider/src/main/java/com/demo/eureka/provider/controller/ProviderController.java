package com.demo.eureka.provider.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author NekoChips
 * @description 服务提供controller
 * @date 2020/3/23
 */
@RestController
public class ProviderController {

    @GetMapping("echo/{message}")
    public HttpEntity<?> echo(@PathVariable String message) {
        return ResponseEntity.ok("Hello, this is an provider message: " + message);
    }
}
