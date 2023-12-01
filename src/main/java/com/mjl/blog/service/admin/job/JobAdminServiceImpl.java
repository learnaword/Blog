package com.mjl.blog.service.admin.job;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mjl.blog.common.enums.CommonStatusEnum;
import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.job.vo.*;
import com.mjl.blog.convert.admin.JobAdminConvert;
import com.mjl.blog.dal.dataobject.JobDO;
import com.mjl.blog.dal.mysql.JobMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobAdminServiceImpl implements JobAdminService {
    @Resource
    private JobMapper jobMapper;

    @Override
    public List<JobDO> getJobList() {
        return jobMapper.selectList(JobDO::getStatus, CommonStatusEnum.ENABLE.getStatus());
    }

    @Override
    public JobDO getJobById(Long id) {
        return jobMapper.selectById(id);
    }

    @Override
    public void updateStatus(UpdateStatusReqVO updateStatusReqVO) {
        List<JobDO> jobDOList =  jobMapper.selectList(new LambdaQueryWrapper<JobDO>().in(JobDO::getId,updateStatusReqVO.getIds()));
        jobDOList.forEach(item -> item.setStatus(updateStatusReqVO.getStatus()).setUpdateTime(System.currentTimeMillis()));
        jobMapper.updateBatch(jobDOList);
    }

    @Override
    public PageResult<JobDO> getList(TableReqVO tableReqVO) {
        return jobMapper.selectPage(tableReqVO);
    }

    @Override
    public void update(UpdateReqVO updateReqVO) {
        JobDO jobDO = JobAdminConvert.INSTANCE.convert(updateReqVO);
        jobDO.setUpdateTime(System.currentTimeMillis());
        jobMapper.updateById(jobDO);
    }

    @Override
    public int create(CreateReqVO createReqVO) {
        JobDO jobDO = JobAdminConvert.INSTANCE.convert(createReqVO);
        jobDO.setStatus(CommonStatusEnum.ENABLE.getStatus());
        jobDO.setCreateTime(System.currentTimeMillis());
        jobDO.setUpdateTime(System.currentTimeMillis());
        return jobMapper.insert(jobDO);
    }

    @Override
    public JobDO getById(Long id) {
        return jobMapper.selectById(id);
    }

    @Override
    public void delete(DeleteReqVO deleteReqVO) {
        jobMapper.deleteBatchIds(deleteReqVO.getIds());
    }

    @Override
    public List<JobDO> getJobList(List<Long> adTypes) {
        return jobMapper.selectBatchIds(adTypes);
    }

    @Override
    public List<JobDO> getJobListSortByList(List<Integer> adTypes) {
        List<Long> longList = adTypes.stream().map(item -> item.longValue()).collect(Collectors.toList());;
        List<JobDO> jobDOList = jobMapper.selectBatchIds(longList);
        Collections.sort(jobDOList, Comparator.comparingLong(item -> longList.indexOf(item.getId())));
        return jobDOList;
    }

}
