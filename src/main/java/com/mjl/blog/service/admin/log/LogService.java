package com.mjl.blog.service.admin.log;

import com.mjl.blog.controller.admin.echars.vo.DataRespVO;
import com.mjl.blog.dal.dataobject.LogDO;

public interface LogService {
    void insert(LogDO logDO);

    DataRespVO getEcharsLogData(Long start, Long end);

    Long getLogCounts();

    Long getLogNowCounts();
}
