package com.mjl.blog.controller.admin.auto.blog;

import com.mjl.blog.common.pojo.CommonResult;
import com.mjl.blog.service.admin.auto.blog.AutoBlogAdminService;
import com.mjl.blog.controller.admin.auto.blog.vo.CreateReqVO;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.mjl.blog.common.pojo.CommonResult.success;

@RequestMapping("/admin/auto-blog")
@RestController
public class AutoBlogAdminController {

    @Resource
    private AutoBlogAdminService autoBlogService;
    @PostMapping("/create")
    public CommonResult<Boolean> create(@RequestBody  CreateReqVO createReqVO){
        autoBlogService.create(createReqVO);
        return success(true);
    }
}
