package com.mjl.blog.service.admin.job;

import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.job.vo.*;
import com.mjl.blog.dal.dataobject.JobDO;
import org.quartz.SchedulerException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface JobAdminService {
    List<JobDO> getJobList();

    void updateStatus(UpdateStatusReqVO updateStatusReqVO);

    PageResult<JobDO> getList(TableReqVO tableReqVO);

    void updateJob(UpdateReqVO updateReqVO) throws SchedulerException;

    @Transactional(rollbackFor = Exception.class)
    Long createJob(CreateReqVO createReqVO) throws SchedulerException;

    JobDO getById(Long id);

    void delete(DeleteReqVO deleteReqVO);

    void updateJobStatus(Long id, Integer status) throws SchedulerException;

    void deleteJob(Long id) throws SchedulerException;

    void triggerJob(Long id) throws SchedulerException;
}
