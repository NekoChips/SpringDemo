package com.demo.redisson.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @author NekoChips
 * @description Redisson 配置类
 * @date 2020/3/27
 */
@Configuration
public class RedissonConfig {

    private Logger logger = LoggerFactory.getLogger(RedissonConfig.class);

    @Bean
    public RedissonClient redissonClient() {
        try {
            Config config = Config.fromYAML(RedissonConfig.class.getClassLoader().getResource("redisson.yaml"));
            RedissonClient redission = Redisson.create(config);
            return redission;
        } catch (IOException e) {
            logger.error("file not found which name is redisson.yaml");
        }
        return null;
    }
}
