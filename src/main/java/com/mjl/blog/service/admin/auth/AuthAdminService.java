package com.mjl.blog.service.admin.auth;

import com.mjl.blog.controller.admin.auth.vo.LoginReqVO;
import com.mjl.blog.controller.admin.auth.vo.LoginRespVO;

public interface AuthAdminService {
    LoginRespVO login(LoginReqVO loginReqVO);
}
