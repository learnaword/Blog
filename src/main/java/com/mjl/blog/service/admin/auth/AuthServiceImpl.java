package com.mjl.blog.service.admin.auth;

import com.mjl.blog.common.exception.utils.ServiceExceptionUtil;
import com.mjl.blog.controller.admin.auth.vo.LoginReqVO;
import com.mjl.blog.controller.admin.auth.vo.LoginRespVO;
import com.mjl.blog.convert.AuthConvert;
import com.mjl.blog.dal.dataobject.AdminUserDO;
import com.mjl.blog.dal.dataobject.SystemAccessTokenDO;
import com.mjl.blog.dal.mysql.AdminUserMapper;
import com.mjl.blog.service.admin.token.TokenService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.mjl.blog.common.exception.utils.ServiceExceptionUtil.exception;
import static com.mjl.blog.enums.ErrorCodeConstants.PASSWORD_ERROR;
import static com.mjl.blog.enums.ErrorCodeConstants.USERNAME_NOT_FOUND;

@Service
public class AuthServiceImpl implements AuthService{

    @Resource
    private AdminUserMapper adminUserMapper;

    @Resource
    private TokenService tokenService;

    @Override
    public LoginRespVO login(LoginReqVO loginReqVO) {
        //检验用户名和密码
        AdminUserDO adminUserDO = authenticate(loginReqVO);
        //如果成功就生成token
        return createToken(adminUserDO.getId(), loginReqVO);
    }

    private LoginRespVO createToken(Long userId,LoginReqVO loginReqVO) {
        //生成access_token。
        //创建access_token需要创建refresh_token，所以创建refresh_token在createAccessToken
        SystemAccessTokenDO systemAccessTokenDO = tokenService.createAccessToken(userId,loginReqVO);

        return AuthConvert.INSTANCE.convert(systemAccessTokenDO);
    }

    public AdminUserDO authenticate(LoginReqVO loginReqVO){
        //根据username从数据获取到AdminUserDO
        AdminUserDO adminUserDO = adminUserMapper.getAdminUserByUsername(loginReqVO.getUsername());
        if(adminUserDO == null){
            throw exception(USERNAME_NOT_FOUND);
        }

        if(!adminUserDO.getPassword().equals(loginReqVO.getPassword())){
            throw exception(PASSWORD_ERROR);
        }

        return adminUserDO;
    }
}
