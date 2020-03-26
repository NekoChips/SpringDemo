package com.demo.log.service;

import com.demo.log.bean.Employee;

import java.util.List;

/**
 * @author NekoChips
 * @description 员工逻辑处理层接口
 * @date 2020/3/25
 */
public interface IEmployeeService {

    /**
     * 新增员工
     *
     * @param employee 员工信息
     * @return
     */
    int add(Employee employee);

    /**
     * 删除员工
     *
     * @param emNo 员工编号
     * @return
     */
    int delete(String emNo);

    /**
     * 更新员工信息
     *
     * @param employee 员工信息
     * @return
     */
    int update(Employee employee);

    /**
     * 查询员工信息
     *
     * @param emNo 员工编号
     * @return
     */
    Employee queryById(String emNo);

    /**
     * 查询员工列表
     *
     * @return
     */
    List<Employee> queryList();
}
