package com.mjl.blog.controller.blog;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BlogController {

    @Resource
    MybatisPlusInterceptor mybatisPlusInterceptor;

    @RequestMapping("/login")
    public String login(){
        System.out.println(111111);
        return "login";
    }

    @RequestMapping("/")
    public String index(){
        System.out.println(2111111);
        return "index";
    }
}
