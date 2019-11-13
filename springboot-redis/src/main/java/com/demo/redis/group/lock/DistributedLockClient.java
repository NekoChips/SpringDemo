package com.demo.redis.group.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;

/**
 * ClassName: DistributedLock <br/>
 * Description: DistributedLock 相关<br/>
 * date: 2019/11/12 18:03<br/>
 *
 * @author Chenyangjie<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Component
public class DistributedLockClient
{
    private Logger logger = LoggerFactory.getLogger(DistributedLockClient.class);

    private static final String SET_IF_NOT_EXIST = "NX";

    private static final String SET_WITH_EXPIRE_TIME = "PX";

    private static final String RESUlT_SUCCESS = "OK";

    private Jedis redisTemplate;

    /**
     * 获取分布式锁
     * @param lock 锁的名称
     * @param requestId 请求锁的id
     * @param expireTime 过期时间
     */
    public void getDistributedLock(String lock, String requestId, long expireTime)
    {
        if (tryGetDistributedLock(lock, requestId ,expireTime))
        {

        }
    }

    private boolean tryGetDistributedLock(String lock, String requestId, long expireTime)
    {
        String resultCode = redisTemplate.set(lock, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
        if(!StringUtils.isEmpty(resultCode) && RESUlT_SUCCESS.equals(resultCode))
        {
            // success to get the lock
            return true;
        }
        return false;
    }

    public void releaseDistributedLock(String lock, String requestId)
    {
        if (tryReleaseDistributedLock(lock, requestId))
        {

        }
    }

    private boolean tryReleaseDistributedLock(String lock, String requestId)
    {
        return false;
    }
}
