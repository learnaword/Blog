package com.mjl.blog.service.admin.log;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mjl.blog.common.utils.DateUtils;
import com.mjl.blog.controller.admin.echars.vo.DataRespVO;
import com.mjl.blog.dal.dataobject.LogDO;
import com.mjl.blog.dal.mysql.LogMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;


@Service
public class LogServiceImpl implements LogService{

    @Resource
    LogMapper logMapper;

    @Override
    public void insert(LogDO logDO) {
        logMapper.insert(logDO);
    }

    @Override
    public DataRespVO getEcharsLogData(Long start, Long end) {
        DataRespVO LogDataRespVO = new DataRespVO();
        int diffNum = (int) TimeUnit.MILLISECONDS.toDays(end - start) + 1;
        LocalDate startTime = Instant.ofEpochMilli(start).atZone(ZoneId.systemDefault()).toLocalDate();
        Long[] counts = new Long[diffNum];
        String[] days =  new String[diffNum];
        Long total = 0L;

        for (int i = 0; i < diffNum; i++) {
            Long preDate = startTime.plusDays(i).atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
            Long nowDate = startTime.plusDays(i+1).atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();;
            Long sum = logMapper.selectCount(new LambdaQueryWrapper<LogDO>()
                    .between(LogDO::getCreateTime,preDate,nowDate));
            counts[i] = sum;
            days[i] = DateUtils.timestampToString(preDate);
            total = total + sum;
        }
        LogDataRespVO.setCounts(counts).setDays(days).setTotal(total);
        return LogDataRespVO;
    }

    @Override
    public Long getLogCounts() {
        return logMapper.selectCount();
    }

    @Override
    public Long getLogNowCounts() {
        LocalDate currentDate = LocalDate.now();
        // 将时间部分设置为0
        LocalDateTime midnight = LocalDateTime.of(currentDate, LocalTime.MIN);
        // 转换为时间戳
        long midnightTimestamp = midnight.toEpochSecond(ZoneOffset.UTC) * 1000;

        Long counts = logMapper.selectCount(new QueryWrapper<LogDO>().gt("create_time",midnightTimestamp));
        return counts;
    }

}
