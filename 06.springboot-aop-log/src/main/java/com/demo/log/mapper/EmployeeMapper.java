package com.demo.log.mapper;

import com.demo.log.bean.Employee;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author NekoChips
 * @description 员工 数据处理层
 * @date 2020/3/25
 */
@Mapper
public interface EmployeeMapper {

    /**
     * 新增员工
     *
     * @param employee 员工信息
     * @return
     */
    @Insert("insert into tb_employee (EM_NO, EM_NAME, EM_SEX, AGE) values (#{emNo}, #{emName}, #{emSex}, #{age})")
    int add(Employee employee);

    /**
     * 删除员工
     *
     * @param emNo 员工编号
     * @return
     */
    @Delete("delete from tb_employee where EM_NO = #{emNo}")
    int delete(String emNo);

    /**
     * 更新员工信息
     *
     * @param employee 员工信息
     * @return
     */
    @Update("update tb_employee set EM_NAME = #{emName}, EM_SEX = #{emSex}, AGE = #{age} where EM_NO = #{emNo}")
    int update(Employee employee);

    /**
     * 查询员工信息
     *
     * @param emNo 员工编号
     * @return
     */
    @Select("select * from tb_employee where EM_NO = #{emNo}")
    @Results(id = "employeeMap", value = {
            @Result(property = "emNo", column = "EM_NO", javaType = String.class),
            @Result(property = "emName", column = "EM_NAME", javaType = String.class),
            @Result(property = "emSex", column = "EM_SEX", javaType = String.class),
            @Result(property = "age", column = "AGE", javaType = Integer.class)
    })
    Employee queryById(String emNo);

    /**
     * 查询员工列表
     *
     * @return
     */
    @Select("select * from tb_employee")
    @ResultMap("employeeMap")
    List<Employee> queryList();
}
