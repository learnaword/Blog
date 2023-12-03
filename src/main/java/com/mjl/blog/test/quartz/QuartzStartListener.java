package com.mjl.blog.test.quartz;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzStartListener {
    public static void main(String[] args) throws InterruptedException {

        //通过schedulerFactory获取一个调度器
        SchedulerFactory schedulerfactory = new StdSchedulerFactory();
        Scheduler scheduler = null;
        try {
            // 通过schedulerFactory获取一个调度器
            scheduler = schedulerfactory.getScheduler();

            // 创建jobDetail实例，绑定Job实现类
            // 指明job的名称，所在组的名称，以及绑定job类
            JobDetail job = JobBuilder.newJob(HelloQuartz.class).withIdentity("JobName").build();

            // 定义调度触发规则
            //  corn表达式  每五秒执行一次
            Trigger cronTrigger = TriggerBuilder.newTrigger()
                    .withIdentity("cronTrigger")
                    .withSchedule(CronScheduleBuilder.cronSchedule("*/5 * * * * ?"))
                    .forJob(job)
                    .startNow().build();

            // 创建第一个触发器，每隔10秒执行一次
            Trigger simpleTrigger = TriggerBuilder.newTrigger()
                    .withIdentity("simpleTrigger")
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                            .withIntervalInSeconds(10)
                            .withRepeatCount(3))
                    .forJob(job)
                    .startNow().build();

            // 把作业和触发器注册到任务调度中
            scheduler.scheduleJob(cronTrigger);
            scheduler.scheduleJob(simpleTrigger);

            // 启动调度
            scheduler.start();

            Thread.sleep(10000);

            // 停止调度
            scheduler.shutdown();

        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }
}
