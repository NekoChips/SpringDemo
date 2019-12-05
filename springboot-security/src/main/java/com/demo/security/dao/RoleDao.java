package com.demo.security.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.security.bean.entity.Role;

/**
 * ClassName: RoleDao <br/>
 * Description: RoleDao 相关<br/>
 * date: 2019/11/26 17:16<br/>
 *
 * @author Chenyangjie<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Mapper
public interface RoleDao extends BaseMapper<Role>
{
}
