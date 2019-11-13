package com.demo.redis.core;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.redis.RedisApplication;

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
    public void testString()
    {
        redisTool.putString("key1", "strValue1", 60000L);
        // Thread.sleep(1000);
        System.out.println("string : " + redisTool.getValue("key1"));
    }

    @Test
    public void testList()
    {
        List<String> languages = Arrays.asList("Java", "Python", ".Net", "C#", "C++");
        redisTool.putList("key2", languages, null);
        System.out.println("list : " + redisTool.getList("key2"));
    }

    @Test
    public void testHash()
    {
        for (int i = 1; i < 10; i++)
        {
            redisTool.putHash("key3", "hashKey" + i, "hashValue" + i);
        }
        System.out.println("allHashValue : " + redisTool.getAllHashValue("key3"));
        System.out.println("oneHash : " + "hashKey3 -- " + redisTool.getValue("key3", "hashKey3"));
    }

    @Test
    public void testSet()
    {
        for (int i = 1; i < 10; i++)
        {
            redisTool.putSet("key4", "setValue" + i);
        }
        System.out.println("set : " + redisTool.getSet("key4"));
    }

    @Test
    public void testZset()
    {
        for (int i = 1; i < 10; i++)
        {
            redisTool.putZSet("key5", "zsetValue" + i, Math.random());
        }
        System.out.println("zset : " + redisTool.getZSet("key5", 0d, 0.5d));
    }

    @Test
    public void testDelete()
    {
        redisTool.delete("key2");
        System.out.println("list : " + redisTool.getList("key2"));

        redisTool.delete("key3", "hashKey3");
        System.out.println("allHashValue : " + redisTool.getAllHashValue("key3"));
        System.out.println("oneHash : " + "hashKey3 -- " + redisTool.getValue("key3", "hashKey3"));
    }

    @Test
    public void testSetExpireTime() throws InterruptedException
    {
        redisTool.setExpireTime("key3", 1000L);
        redisTool.setExpireTime("key4", 1000L);
        redisTool.setExpireTime("key5", 1000L);

        Thread.sleep(1000);
        System.out.println("allHashValue : " + redisTool.getAllHashValue("key3"));
        System.out.println("set : " + redisTool.getSet("key4"));
        System.out.println("zset : " + redisTool.getZSet("key5", 0d, 1d));
    }

    @Test
    public void testGetNull()
    {
        System.out.println(String.valueOf(redisTool.getValue("key8")));
    }

}
