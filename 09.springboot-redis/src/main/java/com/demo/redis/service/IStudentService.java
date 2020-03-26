package com.demo.redis.service;

import com.demo.redis.bean.Student;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @author NekoChips
 * @description Student 逻辑处理层接口
 * @date 2020/3/24
 */
@CacheConfig(cacheNames = "student")
public interface IStudentService {
    /**
     * 新增学生信息
     *
     * @param student 学生信息
     * @return
     */
    @CachePut(key = "#p0.studentId")
    int add(Student student);

    /**
     * 删除学生信息
     *
     * @param studentId 学生id
     * @return
     */
    @CacheEvict(key = "#p0")
    int delete(String studentId);

    /**
     * 更新学生信息
     *
     * @param student 学生信息
     * @return
     */
    @CachePut(key = "#p0.studentId")
    int update(Student student);

    /**
     * 查询学生信息
     *
     * @param studentId 学生id
     * @return
     */
    @Cacheable(key = "#p0")
    Student queryById(String studentId);

    /**
     * 查询学生列表
     *
     * @return
     */
    List<Student> queryList();
}
