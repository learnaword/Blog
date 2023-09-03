package com.mjl.blog.service.admin.token;


import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.IdUtil;
import com.mjl.blog.common.enums.CommonStatusEnum;
import com.mjl.blog.common.enums.GlobalErrorCodeConstants;
import com.mjl.blog.common.utils.DateUtils;
import com.mjl.blog.controller.admin.auth.vo.LoginReqVO;
import com.mjl.blog.dal.dataobject.SystemAccessTokenDO;
import com.mjl.blog.dal.dataobject.SystemRefreshTokenDO;
import com.mjl.blog.dal.mysql.SystemAccessTokenMapper;
import com.mjl.blog.dal.mysql.SystemRefreshTokenMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.mjl.blog.common.exception.utils.ServiceExceptionUtil.exception0;

@Service
public class TokenServiceImpl implements TokenService{

    @Resource
    SystemAccessTokenMapper systemAccessTokenMapper;
    @Resource
    SystemRefreshTokenMapper systemRefreshTokenMapper;

    private final int ACCESS_TOKEN_EXPIRES_TIME = 120; //分钟
    private final int REFRESH_TOKEN_EXPIRES_TIME = 1200;

    @Override
    public SystemAccessTokenDO createAccessToken(Long userId, LoginReqVO loginReqVO) {
        SystemRefreshTokenDO systemRefreshTokenDO = createRefreshToken(userId,loginReqVO);
        SystemAccessTokenDO accessTokenDO = new SystemAccessTokenDO().setUserId(userId)
                .setAccessToken(generateAccessToken())
                .setStatus(CommonStatusEnum.ENABLE.getStatus())
                .setCreateTime(LocalDateTime.now())
                .setExpires(LocalDateTime.now().plusMinutes(ACCESS_TOKEN_EXPIRES_TIME))
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
                .setExpires(LocalDateTime.now().plusMinutes(REFRESH_TOKEN_EXPIRES_TIME));
        systemRefreshTokenMapper.insert(refreshTokenDO);
        return refreshTokenDO;
    }

    @Override
    public SystemAccessTokenDO checkAccessToken(String token) {
        SystemAccessTokenDO accessTokenDO = getAccessTokenByToken(token);
        if (accessTokenDO == null) {
            throw exception0(GlobalErrorCodeConstants.UNAUTHORIZED.getCode(), "访问令牌不存在");
        }
        if (DateUtils.isExpired(accessTokenDO.getExpires())) {
            throw exception0(GlobalErrorCodeConstants.UNAUTHORIZED.getCode(), "访问令牌已过期");
        }
        return accessTokenDO;
    }

    private SystemAccessTokenDO getAccessTokenByToken(String token) {
        return systemAccessTokenMapper.selectOne(SystemAccessTokenDO::getAccessToken,token);
    }

    private static String generateAccessToken() {
        return IdUtil.fastSimpleUUID();
    }

    private static String generateRefreshToken() {
        return IdUtil.fastSimpleUUID();
    }

}
