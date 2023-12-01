package com.mjl.blog.framework.quartz.config;

import com.mjl.blog.framework.quartz.core.scheduler.SchedulerManager;
import org.quartz.Scheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfiguration {

    @Bean
    public SchedulerManager schedulerManager(Scheduler scheduler){

        return new SchedulerManager(scheduler);
    }

}
