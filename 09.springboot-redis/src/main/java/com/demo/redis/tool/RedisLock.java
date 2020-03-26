package com.demo.redis.tool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ClassName: RedisLock <br/>
 * Description: RedisLock 相关<br/>
 * date: 2019/11/13 11:06<br/>
 *
 * @author Chenyangjie<br />
 * @version 1.0
 * @since JDK 1.8
 */
public abstract class RedisLock
{
    private Logger logger = LoggerFactory.getLogger(RedisLock.class);

    /**
     * 获取锁
     * @param lockKey 锁的key
     * @param requestId 请求id
     * @param expireTime 获取锁的过期时间,单位为毫秒
     * @return
     */
    public boolean acquire(String lockKey, String requestId, Long expireTime)
    {
        if (tryAcquire(lockKey, requestId, expireTime) && null != lockKey)
        {
            logger.info("try acquire lock success, lockKey : {}, requestId : {}", lockKey, requestId);
            return true;
        }
        return false;
    }

    /**
     * 尝试获取锁，通过子类实现
     * @param lockKey 锁的key
     * @param requestId 请求id
     * @param expireTime 获取锁的过期时间,单位为秒
     * @return
     */
    public abstract boolean tryAcquire(String lockKey, String requestId, Long expireTime);

    /**
     * 释放锁
     * @param lockKey 锁的key
     * @param requestId 请求id
     * @return
     */
    public boolean release(String lockKey, String requestId)
    {
        if (tryRelease(lockKey, requestId))
        {
            logger.info("tryRelease lock success, lockKey : {}, requestId : {}", lockKey, requestId);
            return true;
        }
        return false;
    }

    /**
     * 尝试释放锁，通过子类实现
     * @param lockKey
     * @param requestId
     * @return
     */
    public abstract boolean tryRelease(String lockKey, String requestId);
}
