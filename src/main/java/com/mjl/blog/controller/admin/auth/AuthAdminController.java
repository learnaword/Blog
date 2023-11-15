package com.mjl.blog.controller.admin.auth;

import com.mjl.blog.common.pojo.CommonResult;
import com.mjl.blog.controller.admin.auth.vo.LoginReqVO;
import com.mjl.blog.controller.admin.auth.vo.LoginRespVO;
import com.mjl.blog.service.admin.auth.AuthAdminService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/admin")
public class AuthAdminController {

    @Resource
    private AuthAdminService authService;

    @RequestMapping("/login")
    public String toLogin(){
        return "/admin/login";
    }

    @RequestMapping("/index")
    public String index(){
        return "/admin/index";
    }

    @RequestMapping("/checkLogin")
    @ResponseBody
    @Operation(summary = "添加用户接口", description = "可以用来新增用户")
    public CommonResult<LoginRespVO> checkLogin(LoginReqVO loginReqVO){
        LoginRespVO loginRespVO = authService.login(loginReqVO);
        return CommonResult.success(loginRespVO);
    }

}
