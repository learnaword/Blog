package com.mjl.blog.service.admin.job;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mjl.blog.common.enums.CommonStatusEnum;
import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.common.utils.CronUtils;
import com.mjl.blog.controller.admin.job.vo.*;
import com.mjl.blog.convert.admin.JobAdminConvert;
import com.mjl.blog.dal.dataobject.JobDO;
import com.mjl.blog.dal.mysql.JobMapper;
import com.mjl.blog.enums.JobStatusEnum;
import com.mjl.blog.framework.quartz.core.handler.JobHandler;
import com.mjl.blog.framework.quartz.core.scheduler.SchedulerManager;
import jakarta.annotation.Resource;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.mjl.blog.common.exception.utils.ServiceExceptionUtil.exception;
import static com.mjl.blog.common.pojo.CollectionUtils.containsAny;
import static com.mjl.blog.enums.ErrorCodeConstants.*;

@Service
public class JobAdminServiceImpl implements JobAdminService {
    @Resource
    private JobMapper jobMapper;

    @Resource
    private SchedulerManager schedulerManager;

    @Resource
    private ApplicationContext applicationContext;

    @Override
    public List<JobDO> getJobList() {
        return jobMapper.selectList(JobDO::getDeleted, CommonStatusEnum.ENABLE.getStatus());
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
    @Transactional(rollbackFor = Exception.class)
    public void updateJob(UpdateReqVO updateReqVO) throws SchedulerException {
        validateCronExpression(updateReqVO.getCronExpression());

        //检验job类是否存在
        validateHandlerName(updateReqVO.getHandlerName());

        // 校验存在
        JobDO job = validateJobExists(updateReqVO.getId());
        // 只有开启状态，才可以修改.原因是，如果出暂停状态，修改 Quartz Job 时，会导致任务又开始执行
        if (!job.getStatus().equals(JobStatusEnum.NORMAL.getStatus())) {
            throw exception(JOB_UPDATE_ONLY_NORMAL_STATUS);
        }

        // 更新
        JobDO updateObj = JobAdminConvert.INSTANCE.convert(updateReqVO);
        updateObj.setUpdateTime(System.currentTimeMillis());
        fillJobMonitorTimeoutEmpty(updateObj);
        jobMapper.updateById(updateObj);

        // 更新 Job 到 Quartz 中
        schedulerManager.updateJob(job.getHandlerName(), updateReqVO.getHandlerParam(), updateReqVO.getCronExpression(),
                updateReqVO.getRetryCount(), updateReqVO.getRetryInterval());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createJob(CreateReqVO createReqVO) throws SchedulerException {
        validateCronExpression(createReqVO.getCronExpression());

        //检验job类是否存在
        validateHandlerName(createReqVO.getHandlerName());

        // 插入
        JobDO job = JobAdminConvert.INSTANCE.convert(createReqVO);
        job.setCreateTime(System.currentTimeMillis()).setUpdateTime(System.currentTimeMillis());
        job.setStatus(JobStatusEnum.INIT.getStatus());
        fillJobMonitorTimeoutEmpty(job);
        jobMapper.insert(job);

        // 添加 Job 到 Quartz 中
        schedulerManager.addJob(job.getId(), job.getHandlerName(), job.getHandlerParam(), job.getCronExpression(),
                createReqVO.getRetryCount(), createReqVO.getRetryInterval());

        //暂停任务
        schedulerManager.pauseJob(job.getHandlerName());

        // 更新
        JobDO updateObj = JobDO.builder().id(job.getId()).status(JobStatusEnum.STOP.getStatus()).build();
        jobMapper.updateById(updateObj);

        // 返回
        return job.getId();
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
    public void updateJobStatus(Long id, Integer status) throws SchedulerException {
        // 校验 status
        if (!containsAny(status, JobStatusEnum.NORMAL.getStatus(), JobStatusEnum.STOP.getStatus())) {
            throw exception(JOB_CHANGE_STATUS_INVALID);
        }
        // 校验存在
        JobDO job = validateJobExists(id);
        // 校验是否已经为当前状态
        if (job.getStatus().equals(status)) {
            throw exception(JOB_CHANGE_STATUS_EQUALS);
        }
        // 更新 Job 状态
        JobDO updateObj = JobDO.builder().id(id).status(status).updateTime(System.currentTimeMillis()).build();
        jobMapper.updateById(updateObj);

        // 更新状态 Job 到 Quartz 中
        if (JobStatusEnum.NORMAL.getStatus().equals(status)) { // 开启
            schedulerManager.resumeJob(job.getHandlerName());
        } else { // 暂停
            schedulerManager.pauseJob(job.getHandlerName());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteJob(Long id) throws SchedulerException {
        // 校验存在
        JobDO job = validateJobExists(id);

        // 更新
        jobMapper.deleteById(id);

        // 删除 Job 到 Quartz 中
        schedulerManager.deleteJob(job.getHandlerName());
    }

    @Override
    public void triggerJob(Long id) throws SchedulerException {
        // 校验存在
        JobDO job = validateJobExists(id);

        // 触发 Quartz 中的 Job
        schedulerManager.triggerJob(job.getId(), job.getHandlerName(), job.getHandlerParam());
    }
    private JobDO validateJobExists(Long id) {
        JobDO job = jobMapper.selectById(id);
        if (job == null) {
            throw exception(JOB_NOT_EXISTS);
        }
        return job;
    }

    private void validateCronExpression(String cronExpression) {
        if (!CronUtils.isValid(cronExpression)) {
            throw exception(JOB_CRON_EXPRESSION_VALID);
        }
    }

    private void validateHandlerName(String handlerName){
        try {
            applicationContext.getBean(handlerName, JobHandler.class);
        } catch (NoSuchBeanDefinitionException e) {
            // 处理找不到bean的情况
            throw exception(JOB_NOT_EXISTS);
        }
    }

    private static void fillJobMonitorTimeoutEmpty(JobDO job) {
        if (job.getMonitorTimeout() == null) {
            job.setMonitorTimeout(0);
        }
    }

}
