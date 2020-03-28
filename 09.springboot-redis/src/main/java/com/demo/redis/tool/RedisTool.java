package com.demo.redis.tool;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * @author NekoChips
 * @description RedisTemplate 封装
 * @date 2020/3/26
 */
@SuppressWarnings("unchecked")
@Component
public class RedisTool {

    private Logger logger = LoggerFactory.getLogger(RedisTool.class);

    public static final String LOCK_NAME = "cache:lock";

    @Autowired
    @SuppressWarnings("rawtypes")
    private RedisTemplate redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 添加缓存
     *
     * @param key   key
     * @param value 缓存内容
     */
    public void put(Object key, Object value) {
        if (verifyParam(key, value)) {
            redisTemplate.opsForValue().set(key, value);;
        }
    }

    /**
     * 添加缓存
     *
     * @param key        key
     * @param value      缓存内容
     * @param expireTime 过期时间
     */
    public void put(Object key, Object value, Long expireTime) {
        if (verifyParam(key, value)) {
            redisTemplate.opsForValue().set(key, value, Duration.ofSeconds(expireTime));
        }
    }

    /**
     * 添加缓存
     *
     * @param key        key
     * @param value      value
     * @param expireTime 过期时间
     * @param timeUnit   时间单位
     */
    public void put(Object key, Object value, Long expireTime, TimeUnit timeUnit) {
        if (verifyParam(key, value)) {
            redisTemplate.opsForValue().set(key, value, expireTime, timeUnit);
        }
    }

    /**
     * 获取 对象
     *
     * @param key   key
     * @param clazz 对象class
     * @param <T>   对象泛型
     * @return 对象
     */
    public <T> T get(Object key, Class<T> clazz) {
        if (verifyParam(key)) {
            Object value = redisTemplate.opsForValue().get(key);
            if (value != null) {
                try {
                    String jsonStr = objectMapper.writeValueAsString(value);
                    return objectMapper.readValue(jsonStr, clazz);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 获取 字符串
     *
     * @param key key
     * @return 字符串
     */
    public String get(Object key) {
        if (verifyParam(key)) {
            Object o = redisTemplate.opsForValue().get(key);
            return o == null ? null : String.valueOf(o);
        }
        return null;
    }

    /**
     * 删除缓存
     *
     * @param key key
     * @return 是否删除成功
     */
    public Boolean delete(Object key) {
        if (verifyParam(key)) {
            return redisTemplate.delete(key);
        }
        return Boolean.FALSE;
    }

    /**
     * 如果缓存不存在 添加
     *
     * @param key   key
     * @param value value
     */
    public void setIfAbsent(Object key, Object value) {
        if (verifyParam(key, value)) {
            redisTemplate.opsForValue().setIfAbsent(key, value);
        }
    }

    /**
     * 如果缓存不存在 添加
     *
     * @param key        key
     * @param value      value
     * @param expireTime 过期时间
     */
    public void setIfAbsent(Object key, Object value, long expireTime) {
        if (!verifyParam(key, value)) {
            return;
        }
        redisTemplate.opsForValue().setIfAbsent(key, value, Duration.ofSeconds(expireTime));
    }

    /**
     * 检查 键值对合法性
     *
     * @param key   key
     * @param value value
     * @return 是否合法
     */
    private boolean verifyParam(Object key, Object value) {
        if (key == null || value == null) {
            logger.warn("Illegal param, null key or null value.");
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
     * 检查 key 合法性
     *
     * @param key key
     * @return 是否合法
     */
    private boolean verifyParam(Object key) {
        if (key == null) {
            logger.warn("Illegal param, null key.");
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
