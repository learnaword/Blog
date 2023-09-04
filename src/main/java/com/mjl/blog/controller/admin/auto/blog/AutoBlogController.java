package com.mjl.blog.controller.admin.auto.blog;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin/autoblog")
public class AutoBlogController {

    @GetMapping("/getBlog")
    public void getBlog(String title){

    }
}
