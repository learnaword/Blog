package com.mjl.blog.service.admin.log;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.common.utils.DateUtils;
import com.mjl.blog.controller.admin.echars.vo.DataRespVO;
import com.mjl.blog.controller.admin.log.vo.TableReqVO;
import com.mjl.blog.controller.admin.log.vo.TableRespVO;
import com.mjl.blog.dal.dataobject.LogDO;
import com.mjl.blog.dal.mysql.LogMapper;
import com.mjl.blog.dal.redis.RedisLogConstants;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;


@Service
public class LogServiceImpl implements LogService{

    @Resource
    LogMapper logMapper;

    @Resource
    StringRedisTemplate stringRedisTemplate;

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
            Long nowDate = startTime.plusDays(i+1).atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();

            String redisKey = String.format(RedisLogConstants.LOG_COUNTS.getKeyTemplate(), preDate,nowDate);
            String sumStr = stringRedisTemplate.opsForValue().get(redisKey);
            Long sum = sumStr!= null ? Long.valueOf(sumStr) : null;
            if(sumStr == null || i == diffNum-1) {
               sum = logMapper.selectCount(new LambdaQueryWrapper<LogDO>()
                        .between(LogDO::getCreateTime, preDate, nowDate));
                stringRedisTemplate.opsForValue().set(redisKey,sum.toString());
            }

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
        long start = System.currentTimeMillis();
        LocalDate startTime = Instant.ofEpochMilli(start).atZone(ZoneId.systemDefault()).toLocalDate();
        Long midnightTimestamp = startTime.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
        Long counts = logMapper.selectCount(new QueryWrapper<LogDO>().gt("create_time",midnightTimestamp));
        return counts;
    }

    @Override
    public PageResult<LogDO> getList(TableReqVO tableReqVO) {
        return logMapper.selectPage(tableReqVO);
    }

}
