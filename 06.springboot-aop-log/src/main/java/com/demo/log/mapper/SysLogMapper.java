package com.demo.log.mapper;

import com.demo.log.bean.SysLog;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author NekoChips
 * @description SysLog 数据处理层
 * @date 2020/3/26
 */
@Mapper
public interface SysLogMapper {

    /**
     * 保存日志
     *
     * @param sysLog 日志信息
     */
    @Insert("insert into sys_log (USERNAME, IP, OPERATION, METHOD, PARAMS, TIME, CREATE_TIME) values (#{username}, " +
            "#{ipAddress}, #{operation}, #{method}, #{params}, #{time}, #{createTime} )")
    int save(SysLog sysLog);

    /**
     * 查询日志详情
     *
     * @param logNo 日志编号
     * @return 日志详情
     */
    @Select("select * from sys_log where LOG_NO = #{logNo}")
    @Results(id = "logMap", value = {
            @Result(property = "logNo", column = "LOG_NO", javaType = Integer.class),
            @Result(property = "username", column = "USERNAME", javaType = String.class),
            @Result(property = "ipAddress", column = "IP", javaType = String.class),
            @Result(property = "operation", column = "OPERATION", javaType = String.class),
            @Result(property = "method", column = "METHOD", javaType = String.class),
            @Result(property = "params", column = "PARAMS", javaType = String.class),
            @Result(property = "time", column = "TIME", javaType = Long.class),
            @Result(property = "createTime", column = "CREATE_TIME", javaType = String.class)
    })
    SysLog queryByNo(Integer logNo);

    /**
     * 查询历史日志
     *
     * @return 日志列表
     */
    @Select("select USERNAME, IP, OPERATION, METHOD, PARAMS, TIME from sys_log")
    @ResultMap("logMap")
    List<SysLog> queryAll();
}
