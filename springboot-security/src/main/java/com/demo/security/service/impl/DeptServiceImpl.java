package com.demo.security.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.security.bean.entity.Department;
import com.demo.security.dao.DeptDao;
import com.demo.security.dao.UserDao;
import com.demo.security.dao.UserDeptDao;
import com.demo.security.service.DeptService;

/**
 * ClassName: DeptServiceImpl <br/>
 * Description: DeptServiceImpl 相关<br/>
 * date: 2019/11/25 16:03<br/>
 *
 * @author Chenyangjie<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Service
public class DeptServiceImpl implements DeptService
{
    private static Logger logger = LoggerFactory.getLogger(DeptServiceImpl.class);

    @Autowired
    private DeptDao deptDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserDeptDao userDeptDao;

    @Override
    public int addOneDept(Department department) {
        return 0;
    }

    @Override
    public int update(Department department) {
        return 0;
    }

    @Override
    public Department queryDeptById(Integer deptId) {
        return null;
    }

    @Override
    public Department queryDeptByLeader(String leaderId) {
        return null;
    }

    @Override
    public Department queryDeptByName(String deptName) {
        return null;
    }

    @Override
    public int dissolveDept(Integer deptId) {
        return 0;
    }
}
