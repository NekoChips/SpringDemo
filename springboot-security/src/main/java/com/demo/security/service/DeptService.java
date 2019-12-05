package com.demo.security.service;

import com.demo.security.bean.entity.Department;

/**
 * ClassName: DeptService <br/>
 * Description: DeptService 相关<br/>
 * date: 2019/11/25 16:03<br/>
 *
 * @author Chenyangjie<br />
 * @version 1.0
 * @since JDK 1.8
 */
public interface DeptService
{
    /**
     * 新增一个部门
     * @param department 部门信息
     * @return
     */
    int addOneDept(Department department);

    /**
     * 更新部门信息
     * @param department 更新后的部门信息
     * @return
     */
    int update(Department department);

    /**
     * 根据id查询部门信息
     * @param deptId 部门id
     * @return
     */
    Department queryDeptById(Integer deptId);

    /**
     * 根据部门领导的id查询部门信息
     * @param leaderId 领导id
     * @return
     */
    Department queryDeptByLeader(String leaderId);

    /**
     * 通过部门名称查询部门信息
     * @param deptName 部门名称
     * @return
     */
    Department queryDeptByName(String deptName);

    /**
     * 解散部门
     * @param deptId 部门id
     * @return
     */
    int dissolveDept(Integer deptId);

}
