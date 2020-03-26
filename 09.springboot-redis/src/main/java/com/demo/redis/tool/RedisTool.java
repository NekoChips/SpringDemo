package com.demo.redis.tool;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Duration;

/**
 * @author NekoChips
 * @description RedisTemplate 封装
 * @date 2020/3/26
 */
@SuppressWarnings("unchecked")
@Component
public class RedisTool {

    @Autowired
    @SuppressWarnings("rawtypes")
    private RedisTemplate redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 添加缓存
     * @param key key
     * @param object 缓存内容
     */
    public void put(String key, Object object) {
        redisTemplate.opsForValue().set(key, object);
    }

    /**
     * 添加缓存
     * @param key key
     * @param object 缓存内容
     * @param expireTime 过期时间
     */
    public void put(String key, Object object, Long expireTime) {
        redisTemplate.opsForValue().set(key, object, Duration.ofSeconds(expireTime));
    }

    /**
     * 获取 对象
     * @param key key
     * @param clazz 对象class
     * @param <T> 对象泛型
     * @return 对象
     */
    public <T> T get(String key, Class<T> clazz) {
        Object obj = redisTemplate.opsForValue().get(key);
        if (obj != null) {
            try {
                String jsonStr = objectMapper.writeValueAsString(obj);
                return objectMapper.readValue(jsonStr, clazz);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 获取 字符串
     * @param key key
     * @return 字符串
     */
    public String get(String key) {
        Object o = redisTemplate.opsForValue().get(key);
        return o == null ? null : String.valueOf(o);
    }

    /**
     * 删除缓存
     * @param key key
     */
    public boolean delete(String key) {
        //noinspection ConstantConditions
        return redisTemplate.delete(key);
    }
}
