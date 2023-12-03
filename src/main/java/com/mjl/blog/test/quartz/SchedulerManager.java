package com.mjl.blog.test.quartz;

import jakarta.annotation.Resource;
import org.quartz.*;
import org.springframework.context.ApplicationContext;


public class SchedulerManager {

    Scheduler scheduler;

    @Resource
    private ApplicationContext applicationContext;

    public SchedulerManager(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    //添加任务
    public void addJob(String jobName, String cronExpression, Integer retryCount, Integer retryInterval) throws SchedulerException {

        Job jobHandler = applicationContext.getBean(jobName, Job.class);
        JobDetail job = JobBuilder.newJob(jobHandler.getClass()).withIdentity(jobName).build();
        //  corn表达式  每五秒执行一次
        Trigger cornTrigger = TriggerBuilder.newTrigger().withIdentity(jobName, "corn")
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                .forJob(job)
                .build();

        // 重试trigger
        Trigger repeaTrigger = TriggerBuilder.newTrigger()
                .withIdentity(jobName, "repeat")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(retryInterval)
                        .withRepeatCount(retryCount)
                )
                .forJob(job)
                .build();

        // 把作业和触发器注册到任务调度中
        scheduler.scheduleJob(cornTrigger);
        scheduler.scheduleJob(repeaTrigger);
    }

    //停止任务
    public void pauseJob(String jobKey) throws SchedulerException {
        scheduler.pauseJob(new JobKey(jobKey));
    }

    //开始任务
    public void resumeJob(String jobKey) throws SchedulerException {
        scheduler.resumeJob(new JobKey(jobKey));
    }

    //删除任务
    public void deleteJob(String jobKey) throws SchedulerException {
        scheduler.deleteJob(new JobKey(jobKey));
    }

    //任务执行一次
    public void triggerJob(Long jobId, String jobHandlerName, String jobHandlerParam)
            throws SchedulerException {
        // 触发任务
        JobDataMap data = new JobDataMap(); // 无需重试，所以不设置 retryCount 和 retryInterval
        scheduler.triggerJob(new JobKey(jobHandlerName), data);
    }

    public void updateJob(String jobName, String cronExpression, Integer retryCount, Integer retryInterval) throws SchedulerException {

        //  corn表达式  每五秒执行一次
        Trigger cornTrigger = TriggerBuilder.newTrigger().withIdentity(jobName, "corn")
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                .forJob(jobName)
                .build();

        // 重试trigger
        Trigger repeaTrigger = TriggerBuilder.newTrigger()
                .withIdentity(jobName, "repeat")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(retryInterval)
                        .withRepeatCount(retryCount)
                )
                .forJob(jobName)
                .build();

        scheduler.rescheduleJob(new TriggerKey(jobName, "corn"), cornTrigger);
        scheduler.rescheduleJob(new TriggerKey(jobName, "repeat"), repeaTrigger);
    }

}
