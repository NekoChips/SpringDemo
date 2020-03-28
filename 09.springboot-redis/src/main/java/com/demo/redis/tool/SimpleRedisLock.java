package com.demo.redis.tool;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ClassName: SimpleRedisLock <br/>
 * Description: SimpleRedisLock 相关<br/>
 * date: 2019/11/13 11:24<br/>
 *
 * @author Chenyangjie<br />
 * @version 1.0
 * @since JDK 1.8
 */
public class SimpleRedisLock extends RedisLock {
    private Logger logger = LoggerFactory.getLogger(SimpleRedisLock.class);

    private Long waitTime = 3000L;

    public SimpleRedisLock(RedisTool redisTool) {
        super(redisTool);
    }

    @Override
    public Boolean tryAcquire() {
        long startMills = System.currentTimeMillis();
        String lockUUID = getLockUUID();
        logger.debug("{} try to acquire the lock {}", Thread.currentThread().getName(), lockUUID);
        while (System.currentTimeMillis() - startMills < waitTime) {
            if (!isLocked()) {
                return Boolean.TRUE;
            }
        }
        logger.debug("{} try to acquire lock {} timeout", Thread.currentThread().getName(), lockUUID);
        return Boolean.FALSE;
    }

    @Override
    public Boolean tryRelease() {
        String lockUUID = getLockUUID();
        String existLock = redisTool.get(RedisTool.LOCK_NAME);
        if (!isLocked()) {
            logger.debug("lock {} has been released", lockUUID);
            return Boolean.TRUE;
        }
        if (StringUtils.equals(lockUUID, existLock)) {
            logger.debug("lock {} can be released", lockUUID);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}