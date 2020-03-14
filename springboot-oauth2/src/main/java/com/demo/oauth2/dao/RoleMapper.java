package com.demo.oauth2.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.oauth2.bean.RoleEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Yangjie.Chen
 * @description 角色数据处理层
 * @date 2020/3/12
 */
@Mapper
public interface RoleMapper extends BaseMapper<RoleEntity> {
}
