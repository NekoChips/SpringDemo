package com.demo.sso.auth.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.sso.auth.bean.AuthorityEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Yangjie.Chen
 * @description 权限数据处理层
 * @date 2020/3/12
 */
@Mapper
public interface AuthMapper extends BaseMapper<AuthorityEntity> {
}
