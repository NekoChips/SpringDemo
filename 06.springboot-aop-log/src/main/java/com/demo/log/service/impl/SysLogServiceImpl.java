package com.demo.log.service.impl;

import com.demo.log.bean.SysLog;
import com.demo.log.mapper.SysLogMapper;
import com.demo.log.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author NekoChips
 * @description ISysLogService 接口实现类
 * @date 2020/3/26
 */
@Service
public class SysLogServiceImpl implements ISysLogService {

    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public int record(SysLog sysLog) {
        return sysLogMapper.save(sysLog);
    }

    @Override
    public SysLog info(Integer logNo) {
        return sysLogMapper.queryByNo(logNo);
    }

    @Override
    public List<SysLog> queryAll() {
        return sysLogMapper.queryAll();
    }
}
