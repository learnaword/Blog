package com.mjl.blog.service.admin.token;


import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.IdUtil;
import com.mjl.blog.common.enums.CommonStatusEnum;
import com.mjl.blog.controller.admin.auth.vo.LoginReqVO;
import com.mjl.blog.dal.dataobject.SystemAccessTokenDO;
import com.mjl.blog.dal.dataobject.SystemRefreshTokenDO;
import com.mjl.blog.dal.mysql.SystemAccessTokenMapper;
import com.mjl.blog.dal.mysql.SystemRefreshTokenMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TokenServiceImpl implements TokenService{

    @Resource
    SystemAccessTokenMapper systemAccessTokenMapper;
    @Resource
    SystemRefreshTokenMapper systemRefreshTokenMapper;

    private final int ACCESS_TOKEN_EXPIRES_TIME = 20;
    private final int REFRESH_TOKEN_EXPIRES_TIME = 40;

    @Override
    public SystemAccessTokenDO createAccessToken(Long userId, LoginReqVO loginReqVO) {
        SystemRefreshTokenDO systemRefreshTokenDO = createRefreshToken(userId,loginReqVO);
        SystemAccessTokenDO accessTokenDO = new SystemAccessTokenDO().setUserId(userId)
                .setAccessToken(generateAccessToken())
                .setStatus(CommonStatusEnum.ENABLE.getStatus())
                .setCreateTime(LocalDateTime.now())
                .setExpires(LocalDateTime.now().plusSeconds(ACCESS_TOKEN_EXPIRES_TIME))
                .setRefreshToken(systemRefreshTokenDO.getRefreshToken());
        systemAccessTokenMapper.insert(accessTokenDO);
        return accessTokenDO;
    }

    @Override
    public SystemRefreshTokenDO createRefreshToken(Long userId, LoginReqVO loginReqVO) {
        SystemRefreshTokenDO refreshTokenDO = new SystemRefreshTokenDO().setUserId(userId)
                .setRefreshToken(generateRefreshToken())
                .setStatus(CommonStatusEnum.ENABLE.getStatus())
                .setCreateTime(LocalDateTime.now())
                .setExpires(LocalDateTime.now().plusSeconds(REFRESH_TOKEN_EXPIRES_TIME));
        systemRefreshTokenMapper.insert(refreshTokenDO);
        return refreshTokenDO;
    }
    private static String generateAccessToken() {
        return IdUtil.fastSimpleUUID();
    }

    private static String generateRefreshToken() {
        return IdUtil.fastSimpleUUID();
    }

}
