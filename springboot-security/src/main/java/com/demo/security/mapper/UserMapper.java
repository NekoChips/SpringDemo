package com.demo.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.security.bean.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Yangjie.Chen
 * @description 用户数据处理层
 * @date 2020/3/12
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
}
