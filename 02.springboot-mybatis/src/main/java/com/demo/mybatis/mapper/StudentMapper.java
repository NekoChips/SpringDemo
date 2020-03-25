package com.demo.mybatis.mapper;

import com.demo.mybatis.bean.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author NekoChips
 * @description Student 数据处理层
 * @date 2020/3/24
 */
@Mapper
public interface StudentMapper {

    /**
     * 新增学生信息
     *
     * @param student 学生信息
     * @return
     */
    @Insert("insert into tb_student (S_ID, S_NAME, SEX) values (#{studentId}, #{name}, #{sex})")
    int add(Student student);

    /**
     * 删除学生信息
     *
     * @param studentId 学生id
     * @return
     */
    @Delete("delete from tb_student where S_ID = #{studentId}")
    int delete(String studentId);

    /**
     * 更新学生信息
     *
     * @param student 学生信息
     * @return
     */
    @Update("update tb_student set S_NAME = #{name}, SEX = #{sex} where S_ID = #{studentId}")
    int update(Student student);

    /**
     * 查询学生信息
     *
     * @param studentId 学生id
     * @return
     */
    @Select("select S_ID, S_NAME, SEX from tb_student where S_ID = #{studentId}")
    @Results(id = "studentMap", value = {
            @Result(property = "studentId", column = "S_ID", javaType = String.class),
            @Result(property = "name", column = "S_NAME", javaType = String.class),
            @Result(property = "sex", column = "SEX", javaType = String.class)
    })
    Student queryById(String studentId);

    /**
     * 查询学生列表
     *
     * @return
     */
    @Select("select * from tb_student")
    @ResultMap("studentMap")
    List<Student> queryList();
}
