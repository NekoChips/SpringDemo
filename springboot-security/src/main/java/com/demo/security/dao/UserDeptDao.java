package com.demo.security.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.security.bean.entity.UserDept;

/**
 * ClassName: UserDeptDao <br/>
 * Description: UserDeptDao 相关<br/>
 * date: 2019/11/26 14:55<br/>
 *
 * @author Chenyangjie<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Mapper
public interface UserDeptDao extends BaseMapper<UserDept>
{
}
