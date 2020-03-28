package com.demo.redisson;

import com.demo.redisson.bean.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author NekoChips
 * @description Redisson 测试类
 * @date 2020/3/28
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedissonDemo {

    private Logger logger = LoggerFactory.getLogger(TestRedissonDemo.class);

    private static final String LOCK_NAME = "lock:001";

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 测试 RBucket
     */
    @Test
    public void testSaveAndGet() {
        Student student = new Student("001", "Jimmy", "F");

        RBucket<Student> rBucket = redissonClient.getBucket("cache:student:" + student.getStudentId());
        rBucket.set(student, 30000L, TimeUnit.MILLISECONDS);

        System.out.println(rBucket.get());
        redissonClient.shutdown();
    }

    /**
     * 测试 RList
     */
    @Test
    public void testRList() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("001", "Jimmy", "F"));
        students.add(new Student("002", "Jack", "M"));
        students.add(new Student("003", "Julia", "F"));

        RList<Student> rList = redissonClient.getList("cache:list");
        rList.clear();

        // addAll
        rList.addAll(students);
        // add
        rList.add(new Student("004", "Jane", "F"));
        // addAfter
        rList.addAfter(new Student("001", "Jimmy", "F"), new Student("005", "Neko", "M"));
        // get(int index)
        System.out.println("index 1 : " + rList.get(1));

        // 获取下标为 0 和 3 的两个元素组成的列表
        // get(int...indexes)
        rList.get(0, 3).forEach(System.out::println);
        redissonClient.shutdown();
    }

    /**
     * 测试 RMap
     */
    @Test
    public void testRMap() {
        RMap<String, Student> rMap = redissonClient.getMap("cache:map");
        rMap.clear();

        rMap.put("001", new Student("001", "Jimmy", "F"));
        rMap.put("002", new Student("002", "Jack", "M"));
        rMap.put("003", new Student("003", "Julia", "F"));
        rMap.put("004", new Student("004", "Jane", "F"));

        // containsKey
        logger.info("rMap contains key '005' : {}", rMap.containsKey("005"));
        // size
        logger.info("rMap size : {}", rMap.size());
        // get(Object key)
        logger.info("rMap get value by '002' : {}", rMap.get("002"));
        // putIfAbsent
        logger.info("rMap put value in existed key '001' : {}", rMap.putIfAbsent("001", new Student()));
        redissonClient.shutdown();
    }

    /**
     * 测试 RAtomicLong
     */
    @Test
    public void testRAtomic() {
        RAtomicLong rAtomicLong = redissonClient.getAtomicLong("cache:atomic:long");
        // get
        logger.info("Init value : {}", rAtomicLong.get());
        // incrementAndGet
        logger.info("After increment value: {}", rAtomicLong.incrementAndGet());
        // addAndGet
        logger.info("After add value : {}", rAtomicLong.addAndGet(100L));

        RAtomicDouble rAtomicDouble = redissonClient.getAtomicDouble("cache:atomic:double");
        // set
        rAtomicDouble.set(10.0D);
        // get
        logger.info("Set value : {}", rAtomicDouble.get());
        // incrementAndGet
        logger.info("After increment value :{}", rAtomicDouble.incrementAndGet());
        // decrementAndGet
        logger.info("After decrement value :{}", rAtomicDouble.decrementAndGet());
        // compareAndSet
        rAtomicDouble.compareAndSet(10.0D, 13.3D);
        logger.info("After compare and set value :{}", rAtomicDouble.get());

        redissonClient.shutdown();
    }

    /**
     * 测试 Redisson 的锁功能
     */
    @Test
    public void testLock() throws InterruptedException {
        RLock lock = redissonClient.getLock(LOCK_NAME);
        // 设置 10秒 后自动释放锁
        lock.lock(10,TimeUnit.SECONDS);
        logger.info("main acquired lock {}", LOCK_NAME);

        Thread thread = new Thread(() -> {
            RLock tLock = redissonClient.getLock(LOCK_NAME);
            tLock.lock();
            logger.info("thread acquired lock {}", LOCK_NAME);
            tLock.unlock();
            logger.info("thread released lock {}", LOCK_NAME);
        });

        thread.start();
        // 通过 unlock 手动释放锁
        lock.unlock();
        logger.info("main released lock {}", LOCK_NAME);
        thread.join();
        redissonClient.shutdown();
    }
}
