package com.demo.redis.core;


import com.demo.redis.RedisApplication;
import com.demo.redis.tool.RedisLock;
import com.demo.redis.tool.RedisTool;
import com.demo.redis.tool.SimpleRedisLock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

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
    private Logger logger = LoggerFactory.getLogger(TestSimpleRedisLock.class);

    @Autowired
    private RedisTool redisTool;

    @Test
    public void testSimpleRedisLock() throws InterruptedException
    {
        RedisLock lock = new SimpleRedisLock(redisTool);
        lock.setLockUUID(UUID.randomUUID().toString());

        // 2秒后自动释放锁
        lock.acquire(2L, TimeUnit.SECONDS);
        logger.info("main acquired lock {}", lock.getLockUUID());

        Thread thread = new Thread(() -> {
            lock.acquire();
            logger.info("thread acquire lock {}", lock.getLockUUID());
            lock.release();
            logger.info("thread release lock {}", lock.getLockUUID());
        });

        thread.start();
        // 手动释放锁，
//        lock.release();
//        logger.info("main release lock {}", lock.getLockUUId());
        thread.join();
    }
}
