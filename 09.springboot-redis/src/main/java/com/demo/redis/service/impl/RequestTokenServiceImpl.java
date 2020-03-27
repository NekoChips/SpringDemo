package com.demo.redis.service.impl;

import com.demo.redis.service.IRequestTokenService;
import com.demo.redis.tool.RedisTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author NekoChips
 * @description RequestToken 实现类
 * @date 2020/3/27
 */
@Service
public class RequestTokenServiceImpl implements IRequestTokenService {

    private static final String CACHE_PREFIX = "cache:request:";

    @Autowired
    private RedisTool redisTool;

    @Override
    public String createToken() {
        String token = String.valueOf(System.currentTimeMillis());
        // 添加至缓存
        redisTool.setIfAbsent(CACHE_PREFIX + token, token);
        return token;
    }
}
