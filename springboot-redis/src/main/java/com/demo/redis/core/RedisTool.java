package com.demo.redis.core;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;


/**
 * ClassName: RedisTool <br/>
 * Description: RedisTool 相关<br/>
 * date: 2019/11/12 18:30<br/>
 *
 * @author Chenyangjie<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Component
public class RedisTool
{
    private Logger logger = LoggerFactory.getLogger(RedisTool.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 存储 String 类型的数据
     * @param key 键
     * @param value 值
     * @param expireTime 过期时间
     * @return
     */
    public boolean putString(String key, String value, Long expireTime)
    {
        try
        {
            if (null != expireTime)
            {
                redisTemplate.opsForValue().set(key, value, expireTime, TimeUnit.MILLISECONDS);
            }
            else
            {
                redisTemplate.opsForValue().set(key, value);
            }
            return true;
        }
        catch (Exception e)
        {
            logger.error("RedisUtil.putString failed, key:{}, value:{}, expireTime:{}", key, value, expireTime);
        }
        return false;
    }

    /**
     * 获取 String 类型的数据
     * @param key 键
     * @return
     */
    public String getValue(String key)
    {
        try
        {
            Object result = redisTemplate.opsForValue().get(key);
            return null == result ? null : String.valueOf(result);
        }
        catch (Exception e)
        {
            logger.error("RedisUtil.getValue failed, key:{}", key);
        }
        return null;
    }

    /**
     * 存储 List 类型的数据
     * @param key 键
     * @param list 值
     * @param expireTime 过期时间
     * @return
     */
    public boolean putList(String key, List list, Long expireTime)
    {
        try
        {
            if (null == expireTime)
            {
                redisTemplate.opsForList().leftPushAll(key, list);
            }
            else
            {
                redisTemplate.opsForList().set(key, expireTime, list);
            }
            return true;
        }
        catch (Exception e)
        {
            logger.error("RedisUtil.putList failed, key:{}, expireTime:{}", key, expireTime);
        }
        return false;
    }

    /**
     * 获取 List 类型的数据
     * @param key 键
     * @return
     */
    public List getList(String key)
    {
        List list = null;
        try
        {
            ListOperations<String, Object> listOperations = redisTemplate.opsForList();
            long size = listOperations.size(key);
            if (size > 0)
            {
                list = listOperations.range(key, 0, size);
            }
        }
        catch (Exception e)
        {
            logger.error("RedisUtil.getList failed, key:{}", key);
        }
        return list;
    }

    /**
     * 向 Hash 中插入数据
     * @param key 键
     * @param hashKey 元素的键
     * @param hashValue 元素的值
     */
    public boolean putHash(String key, String hashKey, String hashValue)
    {
        try
        {
            redisTemplate.opsForHash().put(key, hashKey, hashValue);
            return true;
        }
        catch (Exception e)
        {
            logger.error("RedisUtil.putHash failed, key:{}, hashKey:{}, hashValue:{}", key, hashKey, hashValue);
        }
        return false;
    }

    /**
     * 获取 hash 中的所有元素
     * @param key 键
     * @return
     */
    public Map getAllHashValue(String key)
    {
        try
        {
            return redisTemplate.opsForHash().entries(key);
        }
        catch (Exception e)
        {
            logger.error("RedisUtil.getAllHashValue failed, key:{}", key);
        }
        return null;
    }

    /**
     * 获取 hash 中指定值
     * @param key 键
     * @param hashKey 元素的键
     * @return
     */
    public String getValue(String key, String hashKey)
    {
        try
        {
            Object result = redisTemplate.opsForHash().get(key, hashKey);

            return null == result ? null : String.valueOf(result);
        }
        catch (Exception e)
        {
            logger.error("RedisUtil.getValue failed, key:{}, hashKey:{}", key, hashKey);
        }
        return null;
    }

    /**
     * 存储 Set 类型的数据
     * @param key 键
     * @param value 值
     * @return
     */
    public boolean putSet(String key, String value)
    {
        try
        {
            if (redisTemplate.opsForSet().add(key, value) > 0)
            {
                return true;
            }
        }
        catch (Exception e)
        {
            logger.error("RedisUtil.putSet failed, key:{}, value:{}", key, value);
        }
        return false;
    }

    /**
     * 获取 Set 类型的数据
     * @param key 键
     * @return
     */
    public Set getSet(String key)
    {
        Set set = null;
        try
        {
            set = redisTemplate.opsForSet().members(key);
        }
        catch (Exception e)
        {
            logger.error("RedisUtil.getSet failed, key:{}", key);
        }
        return set;
    }

    /**
     * 存储 Zset 类型的数据
     * @param key 键
     * @param value 值
     * @param score 元素的分值
     * @return
     */
    public boolean putZSet(String key, String value, double score)
    {
        try
        {
            return redisTemplate.opsForZSet().add(key, value, score);
        }
        catch (Exception e)
        {
            logger.error("RedisUtil.putZSet failed, key:{}, value:{}, score:{}", key, value, score);
        }
        return false;
    }

    /**
     * 获取 Redis 的 Zset 类型的数据
     * @param key 键
     * @param minScore 最小分数
     * @param maxScore 最大分数
     * @return
     */
    public Set getZSet(String key, double minScore, double maxScore)
    {
        Set set = null;
        try
        {
            set = redisTemplate.opsForZSet().rangeByScore(key, minScore, maxScore);
        }
        catch (Exception e)
        {
            logger.error("RedisUtil.getZSet failed, key:{}", key);
        }
        return set;
    }

    /**
     * 删除缓存中的数据
     * @param key 键
     * @return
     */
    public boolean delete(String key)
    {
        try
        {
            return redisTemplate.delete(key);
        }
        catch (Exception e)
        {
            logger.error("RedisUtil.delete failed, key:{}" , key);
        }
        return false;
    }

    /**
     * 删除 hash 中的指定元素
     * @param key 键
     * @param hashKey hash键
     * @return
     */
    public boolean delete(String key, String hashKey)
    {
        try
        {
            if (redisTemplate.opsForHash().delete(key, hashKey) > 0L)
            {
                return true;
            }
        }
        catch (Exception e)
        {
            logger.error("RedisUtil.delete failed, key:{}, hashKey:{}" , key, hashKey);
        }
        return false;
    }

    /**
     * 设置键的过期时间
     * @param key 键
     * @param expireTime 过期时间
     * @return
     */
    public boolean setExpireTime(String key, long expireTime)
    {
        try
        {
            return redisTemplate.expire(key, expireTime, TimeUnit.MILLISECONDS);
        }
        catch (Exception e)
        {
            logger.error("RedisUtil.setExpireTime failed, key:{}, expireTime:{}" , key, expireTime);
        }
        return false;
    }
}
