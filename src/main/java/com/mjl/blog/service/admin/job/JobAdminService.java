package com.mjl.blog.service.admin.job;

import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.job.vo.*;
import com.mjl.blog.dal.dataobject.JobDO;

import java.util.List;

public interface JobAdminService {
    List<JobDO> getJobList();
    JobDO getJobById(Long id);

    void updateStatus(UpdateStatusReqVO updateStatusReqVO);

    PageResult<JobDO> getList(TableReqVO tableReqVO);

    void update(UpdateReqVO updateReqVO);

    int create(CreateReqVO createReqVO);

    JobDO getById(Long id);

    void delete(DeleteReqVO deleteReqVO);

    List<JobDO> getJobList(List<Long> adTypes);

    List<JobDO> getJobListSortByList(List<Integer> adTypes);
    }
