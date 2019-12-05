package com.demo.security.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.security.bean.entity.RolePermission;

/**
 * ClassName: RolePermissionDao <br/>
 * Description: RolePermissionDao 相关<br/>
 * date: 2019/11/27 17:32<br/>
 *
 * @author Chenyangjie<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Mapper
public interface RolePermissionDao extends BaseMapper<RolePermission>
{
}
