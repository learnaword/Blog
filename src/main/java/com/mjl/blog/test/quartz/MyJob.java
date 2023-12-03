package com.mjl.blog.test.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class MyJob extends QuartzJobBean {

    private String jobName;

    // Getter and setter for jobName

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        // ...
        System.out.println("Job Name: " + jobName);
    }
}
