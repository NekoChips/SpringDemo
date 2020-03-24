package com.demo.mybatis.mapper;

import com.demo.mybatis.bean.User;
import org.apache.ibatis.annotations.*;

/**
 * @author NekoChips
 * @description User数据库处理层
 * @date 2020/3/24
 */
@Mapper
public interface UserMapper {

    @Select("select * from tb_user_info where u_id = #{userId}")
    @Results(id = "user", value = {
            @Result(property = "userId", column = "U_ID", javaType = String.class),
            @Result(property = "username", column = "U_NAME", javaType = String.class),
            @Result(property = "loginTime", column = "LAST_LOGIN_TIME", javaType = String.class)
    })
    User queryById(String userId);

    @Insert("insert into tb_user_info(U_ID, U_NAME) values(#{userId}, #{username})")
    int insert(User user);

    @Update("update tb_user_info set U_NAME = #{username} where U_ID = #{userId}")
    int update(User user);

    @Delete("delete from tb_user_info where U_ID = #{userId}")
    int deleteById(String userId);
}
