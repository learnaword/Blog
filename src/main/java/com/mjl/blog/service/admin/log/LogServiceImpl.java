package com.mjl.blog.service.admin.log;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mjl.blog.common.utils.DateUtils;
import com.mjl.blog.controller.admin.echars.vo.DataRespVO;
import com.mjl.blog.dal.dataobject.LogDO;
import com.mjl.blog.dal.mysql.LogMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
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
        int diffNum = (int) TimeUnit.MILLISECONDS.toDays(end - start);
        LocalDate today = LocalDate.now();
        Long[] counts = new Long[diffNum];
        String[] days =  new String[diffNum];
        Long total = 0L;

        for (int i = 1; i <= diffNum; i++) {
            Long preDate = today.minusDays(i).atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
            Long nowDate = today.minusDays(i).plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();;
            Long sum = logMapper.selectCount(new LambdaQueryWrapper<LogDO>()
                    .between(LogDO::getCreateTime,preDate,nowDate));
            counts[diffNum-i] = sum;
            days[diffNum-i] = DateUtils.timestampToString(nowDate);
            total = total + sum;
        }
        LogDataRespVO.setCounts(counts).setDays(days).setTotal(total);
        return LogDataRespVO;
    }

}
