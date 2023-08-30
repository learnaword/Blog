package com.mjl.blog.controller.admin.auth;

import com.mjl.blog.controller.admin.auth.vo.LoginReqVO;
import com.mjl.blog.controller.admin.auth.vo.LoginRespVO;
import com.mjl.blog.enums.ErrorCodeConstants;
import com.mjl.blog.service.admin.auth.AuthService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.mjl.blog.common.exception.utils.ServiceExceptionUtil.exception;


@Controller
public class BlogAdminController {

    @Resource
    AuthService authService;

    @RequestMapping("/login")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/checkLogin")
    public String checkLogin(LoginReqVO loginReqVO){
        LoginRespVO loginRespVO = authService.login(loginReqVO);
        throw exception(ErrorCodeConstants.USERNAME_NOT_FOUND);
    }

}
