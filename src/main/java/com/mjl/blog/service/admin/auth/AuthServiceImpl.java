package com.mjl.blog.service.admin.auth;

import com.mjl.blog.controller.admin.auth.vo.LoginReqVO;
import com.mjl.blog.controller.admin.auth.vo.LoginRespVO;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    @Override
    public LoginRespVO login(LoginReqVO loginReqVO) {
        //检验用户名和密码

        //如果成功就生成token
        return null;
    }
}
