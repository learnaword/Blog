package com.mjl.blog.controller.admin.auto.blog;

import com.mjl.blog.service.admin.auto.blog.AutoBlogService;
import com.mjl.blog.controller.admin.auto.blog.vo.CreateReqVO;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admin/auto-blog")
@RestController
public class AutoBlogController {

    @Resource
    private AutoBlogService autoBlogService;
    @PostMapping("/create")
    public void create(@RequestBody  CreateReqVO createReqVO){
        autoBlogService.create(createReqVO);
    }
}
