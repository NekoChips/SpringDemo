package com.demo.security.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.security.bean.entity.Permission;

/**
 * ClassName: PermissionDao <br/>
 * Description: PermissionDao 相关<br/>
 * date: 2019/11/26 17:17<br/>
 *
 * @author Chenyangjie<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Mapper
public interface PermissionDao extends BaseMapper<Permission>
{
}
