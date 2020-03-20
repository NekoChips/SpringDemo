package com.demo.nacos.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author NekoChips
 * @description
 * @date 2020/3/20
 */
@RestController
@RequestMapping("consumer")
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("echo/{message}")
    public String echo(@PathVariable String message) {
        String uri = "http://nacos-provider/provider/echo/";
        return restTemplate.getForObject(uri + message, String.class);
    }

}
