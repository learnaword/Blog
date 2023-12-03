package com.mjl.blog.job;

import com.mjl.blog.framework.quartz.core.handler.JobHandler;
import com.mjl.blog.service.admin.blog.BlogAdminServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class AutoUpdateBlogJob2 implements JobHandler {

    @Resource
    BlogAdminServiceImpl blogAdminService;
    @Override
    public String execute(String param) throws Exception {
        System.out.println("1111111111");
        return "success";
    }
}
