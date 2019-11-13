package com.demo.redis.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.demo.redis.core.RedisTool;

/**
 * ClassName: SimpleRedisLock <br/>
 * Description: SimpleRedisLock 相关<br/>
 * date: 2019/11/13 11:24<br/>
 *
 * @author Chenyangjie<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Component
public class SimpleRedisLock extends RedisLock
{
    private Logger logger = LoggerFactory.getLogger(SimpleRedisLock.class);

    @Autowired
    private RedisTool redisTool;

    @Value("${spring.redis.lock.timeout}")
    private Long waitTime;

    public boolean tryAcquire(String lockKey, String requestId, Long expireTime)
    {
        long requestTimestamp = System.currentTimeMillis();
        while (System.currentTimeMillis() - requestTimestamp < waitTime)
        {
            String targetId = redisTool.getValue(lockKey);
            if (StringUtils.isEmpty(targetId) || requestId.equals(targetId))
            {
                // 锁已被释放或同一个请求再次获取锁（可重入锁），重新上锁并指定 requestId 和过期时间
                logger.info("lock is available, lockKey : {}, requestId : {}", lockKey, requestId);
                return redisTool.putString(lockKey, requestId, expireTime);
            }
        }
        logger.warn(
                "lock is occupied and try acquire lock timeout, please try again later. lockKey : {}, requestId : {}",
                lockKey, requestId);
        return false;
    }

    public boolean tryRelease(String lockKey, String requestId)
    {
        String targetId = redisTool.getValue(lockKey);
        if (StringUtils.isEmpty(targetId))
        {
            logger.warn("lock has acquired or already expired, lockKey : {}", lockKey);
            return true;
        }
        else if (requestId.equals(targetId))
        {
            logger.info("lock can be released, lockKey : {}, requestId : {}", lockKey, requestId);
            return redisTool.delete(lockKey);
        }
        return false;
    }
}
