package com.neko.zuul;

import com.neko.zuul.filter.AuthenticationZuulFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * @author NekoChips
 * @description zuul 网关启动类
 * @date 2020/3/20
 */
@SpringBootApplication
@EnableZuulProxy
public class ZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }

    @Bean
    public AuthenticationZuulFilter authenticationZuulFilter() {
        return new AuthenticationZuulFilter();
    }
}
