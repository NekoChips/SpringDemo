package com.demo.log.service;

import com.demo.log.bean.SysLog;

import java.util.List;

/**
 * @author NekoChips
 * @description 日志逻辑处理层接口
 * @date 2020/3/26
 */
public interface ISysLogService {

    /**
     * 记录日志
     * @param sysLog 日志信息
     * @return 执行成功的数目
     */
    int record(SysLog sysLog);

    /**
     * 查询日志信息
     * @param logNo 日志编号
     * @return 日志信息
     */
    SysLog info(Integer logNo);

    /**
     * 查询所有日志记录
     * @return 日志记录列表
     */
    List<SysLog> queryAll();
}
