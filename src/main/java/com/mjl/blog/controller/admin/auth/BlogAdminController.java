package com.mjl.blog.controller.admin.auth;

import com.mjl.blog.common.pojo.CommonResult;
import com.mjl.blog.controller.admin.auth.vo.LoginReqVO;
import com.mjl.blog.controller.admin.auth.vo.LoginRespVO;
import com.mjl.blog.enums.ErrorCodeConstants;
import com.mjl.blog.service.admin.auth.AuthService;
import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.mjl.blog.common.exception.utils.ServiceExceptionUtil.exception;
import static com.mjl.blog.common.pojo.CommonResult.success;


@Controller
@RequestMapping("/admin")
public class BlogAdminController {

    @Resource
    AuthService authService;

    @RequestMapping("/login")
    @PermitAll
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/index")
    public String index(){
        return "/admin/index";
    }

    @RequestMapping("/checkLogin")
    @ResponseBody
    public CommonResult<LoginRespVO> checkLogin(LoginReqVO loginReqVO){
        LoginRespVO loginRespVO = authService.login(loginReqVO);
        return CommonResult.success(loginRespVO);
    }

}
