package com.mjl.blog.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MyJob extends QuartzJobBean {

    private String jobName;

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        // Print jobName during execution for testing purposes
        BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(this);

        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("jobName","NewJobName2");
        // 通过 BeanWrapper 设置属性值
        bw.setPropertyValues(hashMap);

        // 执行任务逻辑
        System.out.println("Job Name: " + jobName);
    }
}
