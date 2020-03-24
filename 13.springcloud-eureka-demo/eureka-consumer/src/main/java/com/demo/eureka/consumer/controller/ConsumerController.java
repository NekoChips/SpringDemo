package com.demo.eureka.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author NekoChips
 * @description 服务消费者controller
 * @date 2020/3/23
 */
@RestController
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("echo/{message}")
    public HttpEntity<?> echo(@PathVariable String message) {
        String uri = "http://eureka-provider/echo/";
        String result = restTemplate.getForObject(uri + message, String.class);
        return ResponseEntity.ok(result);
    }
}
