package com.demo.consul.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author NekoChips
 * @description 服务提供者启动类
 * @date 2020/3/20
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConsulProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsulProviderApplication.class, args);
    }
}
