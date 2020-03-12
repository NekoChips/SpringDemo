package com.demo.security.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * ClassName: RedisConfig <br/>
 * Description: RedisConfig 相关<br/>
 * date: 2019/11/13 10:04<br/>
 *
 * @author Chenyangjie<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Configuration
public class RedisConfig
{
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 配置RedisTemplate,设置key和value的序列化方式
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate<String, Object> configRedisTemplate()
    {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        RedisSerializer jsonSerializer = new FastJsonRedisSerializer(Object.class);

        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(jsonSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(jsonSerializer);
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        return redisTemplate;
    }
}
