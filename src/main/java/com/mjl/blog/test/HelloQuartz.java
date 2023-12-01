package com.mjl.blog.test;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class HelloQuartz  implements Job {

    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        System.out.println("Hello Quartz !");
    }
}
