package com.demo.consul.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

/**
 * @author NekoChips
 * @description 服务器消费者测试controller
 * @date 2020/3/20
 */
@RestController
@RequestMapping("consumer")
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("echo/{message}")
    public String echo(@PathVariable String message) {
        String serviceName = "consul-provider";
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);
        if (!CollectionUtils.isEmpty(instances)) {
            URI uri = instances.get(0).getUri();
            return restTemplate.getForObject(uri + "/provider/echo/" + message, String.class);
        }
        return "no available service instance";
    }
}
