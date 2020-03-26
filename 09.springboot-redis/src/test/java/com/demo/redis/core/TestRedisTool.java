package com.demo.redis.core;

import com.demo.redis.RedisApplication;
import com.demo.redis.bean.Student;
import com.demo.redis.tool.RedisTool;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
@SpringBootTest(classes = RedisApplication.class)
public class TestRedisTool
{
    @Autowired
    private RedisTool redisTool;

    @Test
    public void string()
    {
        redisTool.put("key1", "strValue1", 60L);
        // Thread.sleep(1000);
        System.out.println("string : " + redisTool.get("key1"));
    }

    @Test
    public void object() {
        Student student = new Student();
        student.setStudentId("001");
        student.setName("Jimmy");
        student.setSex("F");
        redisTool.put("key2", student, 60L);
        System.out.println("key2 : " + redisTool.get("key2"));
    }
}
