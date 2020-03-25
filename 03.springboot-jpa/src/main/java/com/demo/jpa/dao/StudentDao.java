package com.demo.jpa.dao;

import com.demo.jpa.bean.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author NekoChips
 * @description student 数据处理层
 * @date 2020/3/25
 */
public interface StudentDao extends JpaRepository<Student, String> {

    @Query(value = "select student from tb_student student where student.name = :name")
    Student findByName(String name);

    /**
     * 查询列表 （nativeQuery 表示 注解中的语句为原生sql, 默认为false）
     * @param sex 性别
     * @return
     */
    @Query(value = "select * from tb_student where SEX = :sex", nativeQuery = true)
    List<Student> queryListBySex(String sex);
}
