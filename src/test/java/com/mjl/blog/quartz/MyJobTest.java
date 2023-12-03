package com.mjl.blog.quartz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.JobExecutionContext;
import org.quartz.Scheduler;
import org.quartz.SchedulerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyJobTest {

    @Autowired
    private MyJob myJob;

    @Test
    public void testJobExecution() throws Exception {
        // Mock JobExecutionContext
        JobExecutionContext context = mock(JobExecutionContext.class);

        // Mock Scheduler
        Scheduler scheduler = mock(Scheduler.class);
        when(context.getScheduler()).thenReturn(scheduler);

        // Mock SchedulerContext and add properties
        SchedulerContext schedulerContext = new SchedulerContext();
        schedulerContext.put("schedulerKey", "schedulerValue");
        when(scheduler.getContext()).thenReturn(schedulerContext);

        // Before executing the job, verify the initial state of properties
        System.out.println("Initial Job Name: " + myJob.getJobName());

        // Execute the job
        myJob.executeInternal(context);

        // After executing the job, verify the state of properties
        System.out.println("Final Job Name: " + myJob.getJobName());
    }
}
