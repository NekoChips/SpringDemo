package com.demo.redis.tool;

import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * ClassName: RedisLock <br/>
 * Description: RedisLock 相关<br/>
 * date: 2019/11/13 11:06<br/>
 *
 * @author Chenyangjie<br />
 * @version 1.0
 * @since JDK 1.8
 */
public abstract class RedisLock {

    public RedisTool redisTool;

    private String lockUUID;

    public RedisLock(RedisTool redisTool) {
        this.redisTool = redisTool;
    }

    public String getLockUUID() {
        return lockUUID;
    }

    public void setLockUUID(String lockUUID) {
        this.lockUUID = lockUUID;
    }

    /**
     * 加锁
     */
    public void acquire() {
        if (tryAcquire()) {
            redisTool.put(RedisTool.LOCK_NAME, lockUUID);
        }
    }

    /**
     * 加锁一定时间后自动释放锁
     *
     * @param leaseTime 超过该时间，自动释放锁
     * @param timeUnit  时间单位
     */
    public void acquire(Long leaseTime, TimeUnit timeUnit) {
        if (tryAcquire()) {
            redisTool.put(RedisTool.LOCK_NAME, lockUUID, leaseTime, timeUnit);
        }
    }

    /**
     * 尝试加锁
     *
     * @return 是否可以加锁
     */
    public abstract Boolean tryAcquire();

    /**
     * 释放锁
     */
    public void release() {
        if (tryRelease()) {
            redisTool.delete(RedisTool.LOCK_NAME);
        }
    }

    /**
     * 尝试释放锁
     *
     * @return 是否可以释放锁
     */
    public abstract Boolean tryRelease();

    /**
     * 判断锁是否已经上锁
     * @return 是否上锁
     */
    public Boolean isLocked() {
        String existLock = redisTool.get(RedisTool.LOCK_NAME);
        if (StringUtils.isBlank(existLock) || !StringUtils.equals(existLock, lockUUID)) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
