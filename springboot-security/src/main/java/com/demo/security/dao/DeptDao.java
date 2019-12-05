package com.demo.security.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.security.bean.entity.Department;

/**
 * ClassName: DeptDao <br/>
 * Description: DeptDao 相关<br/>
 * date: 2019/11/25 16:04<br/>
 *
 * @author Chenyangjie<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Mapper
public interface DeptDao extends BaseMapper<Department>
{
}
