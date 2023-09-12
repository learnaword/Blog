package com.mjl.blog.service.admin.log;

import com.mjl.blog.dal.dataobject.LogDO;
import com.mjl.blog.dal.mysql.LogMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;


@Service
public class LogServiceImpl implements LogService{

    @Resource
    LogMapper logMapper;

    @Override
    public void insert(LogDO logDO) {
        logMapper.insert(logDO);
    }
}
