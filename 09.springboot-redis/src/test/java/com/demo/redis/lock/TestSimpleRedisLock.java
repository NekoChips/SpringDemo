package com.demo.redis.lock;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.redis.RedisApplication;

/**
 * ClassName: TestSimpleRedisLock <br/>
 * Description: TestSimpleRedisLock 相关<br/>
 * date: 2019/11/13 12:52<br/>
 *
 * @author Chenyangjie<br />
 * @version 1.0
 * @since JDK 1.8
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisApplication.class)
public class TestSimpleRedisLock
{
    private static final String LOCK_KEY = "redisLock";

    private static final Long LOCK_EXPIRE_TIME = 10000L;

    @Autowired
    private RedisLock redisLock;

    @Test
    public void testSimpleRedisLock() throws InterruptedException
    {

        // 首先使用请求 A 获取锁，因为之前没有锁，第一次必定是成功的
        String requestA = "requestA";
        redisLock.acquire(LOCK_KEY, requestA, LOCK_EXPIRE_TIME);

        // 请求 B 去获取锁,3秒内一直尝试获取锁，获取不到就不获取了
        String requestB = "requestB";
        redisLock.acquire(LOCK_KEY, requestB, LOCK_EXPIRE_TIME);

        // 锁的过期时间为10S， 等待10S后请求 B 去获取锁
        Thread.sleep(10000L);
        redisLock.acquire(LOCK_KEY, requestB, LOCK_EXPIRE_TIME);

        // 请求 B 获取到锁后，释放锁
        redisLock.release(LOCK_KEY, requestB);
        // 再次使用请求 A 获取锁
        redisLock.acquire(LOCK_KEY, requestA, LOCK_EXPIRE_TIME);

//        redisLock.release(LOCK_KEY, requestA);

    }

}
