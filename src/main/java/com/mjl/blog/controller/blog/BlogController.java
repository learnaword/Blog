package com.mjl.blog.controller.blog;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BlogController {

    @Resource
    MybatisPlusInterceptor mybatisPlusInterceptor;

    @RequestMapping("/")
    public String index(){
        return "page/index";
    }
}
