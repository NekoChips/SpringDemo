package com.demo.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * ClassName: RedisApplication <br/>
 * Description: RedisApplication 相关<br/>
 * date: 2019/11/12 18:00<br/>
 *
 * @author Chenyangjie<br />
 * @version 1.0
 * @since JDK 1.8
 */
@SpringBootApplication
@EnableEurekaClient
public class RedisApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(RedisApplication.class);
    }
}
