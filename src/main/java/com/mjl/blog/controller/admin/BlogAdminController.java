package com.mjl.blog.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class BlogAdminController {

    @RequestMapping("/login")
    public String toLogin(){
        return "login";
    }

}
