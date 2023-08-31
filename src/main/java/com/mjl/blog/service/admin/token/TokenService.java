package com.mjl.blog.service.admin.token;

import com.mjl.blog.controller.admin.auth.vo.LoginReqVO;
import com.mjl.blog.dal.dataobject.SystemAccessTokenDO;
import com.mjl.blog.dal.dataobject.SystemRefreshTokenDO;

public interface TokenService {
    SystemAccessTokenDO createAccessToken(Long userId, LoginReqVO loginReqVO);
    SystemRefreshTokenDO createRefreshToken(Long userId, LoginReqVO loginReqVO);
}
