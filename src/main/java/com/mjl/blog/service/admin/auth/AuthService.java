package com.mjl.blog.service.admin.auth;

import com.mjl.blog.controller.admin.auth.vo.LoginReqVO;
import com.mjl.blog.controller.admin.auth.vo.LoginRespVO;

public interface AuthService {
    LoginRespVO login(LoginReqVO loginReqVO);
}
