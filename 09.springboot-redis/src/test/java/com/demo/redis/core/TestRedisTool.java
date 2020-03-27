package com.demo.redis.core;

import com.demo.redis.bean.Student;
import com.demo.redis.tool.RedisTool;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * ClassName: TestRedisTool <br/>
 * Description: TestRedisTool 相关<br/>
 * date: 2019/11/12 22:18<br/>
 *
 * @author Chenyangjie<br />
 * @version 1.0
 * @since JDK 1.8
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedisTool {

    private static final String CACHE_PREFIX = "cache:student:";

    @Autowired
    private RedisTool redisTool;

    @Test
    public void cacheWithExpire() throws InterruptedException {
        Student student = new Student();
        student.setStudentId("001");
        student.setName("Jimmy");
        student.setSex("F");
        redisTool.put(CACHE_PREFIX + student.getStudentId(), student, 60L);

        Thread.sleep(2000L);

        Student studentCache = redisTool.get(CACHE_PREFIX + student.getStudentId(), Student.class);
        System.out.println("student from redis : " + studentCache);
    }

    @Test
    public void cacheWithTimeUnit() throws InterruptedException {
        Student student = new Student();
        student.setStudentId("002");
        student.setName("Jack");
        student.setSex("M");
        redisTool.put(CACHE_PREFIX + student.getStudentId(), student, 30000L, TimeUnit.MILLISECONDS);

        Thread.sleep(2000L);

        String strCache = redisTool.get(CACHE_PREFIX + student.getStudentId());
        System.out.println("cache string : " + strCache);

        Student studentCache = redisTool.get(CACHE_PREFIX + student.getStudentId(), Student.class);
        System.out.println("student from redis : " + studentCache);
    }

    @Test
    public void delete() {
        Student student = new Student();
        student.setStudentId("002");
        student.setName("Jack");
        student.setSex("M");
        redisTool.put(CACHE_PREFIX + student.getStudentId(), student, 30000L, TimeUnit.MILLISECONDS);

        Student studentCache = redisTool.get(CACHE_PREFIX + student.getStudentId(), Student.class);
        System.out.println("student from redis : " + studentCache);

        System.out.println("delete cache");
        redisTool.delete(CACHE_PREFIX + student.getStudentId());

        String result = redisTool.get(CACHE_PREFIX + student.getStudentId());
        System.out.println("student from redis : " + result);
    }
}
