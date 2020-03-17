package com.demo.sso.auth.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.sso.auth.bean.UserRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Yangjie.Chen
 * @description 用户角色 数据处理层
 * @date 2020/3/12
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {
}
