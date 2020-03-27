package com.demo.redis.service;

/**
 * @author NekoChips
 * @description RequestToken 接口
 * @date 2020/3/27
 */
public interface IRequestTokenService {

    /**
     * 创建 token
     * @return request token
     */
    String createToken();
}
