package com.mjl.blog.framework.security.core.fileter;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.mjl.blog.common.exception.ServiceException;
import com.mjl.blog.dal.dataobject.AdminUserDO;
import com.mjl.blog.dal.dataobject.SystemAccessTokenDO;
import com.mjl.blog.framework.security.core.LoginUser;
import com.mjl.blog.framework.security.core.util.SecurityFrameworkUtils;
import com.mjl.blog.service.admin.adminUser.AdminUserService;
import com.mjl.blog.service.admin.token.TokenService;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Token 过滤器，验证 token 的有效性
 * 验证通过后，获得 信息，并加入到 Spring Security 上下文
 *
 * @author 芋道源码
 */
@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    @Resource
    TokenService tokenService;

    @Resource
    AdminUserService adminUserService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = SecurityFrameworkUtils.obtainAuthorization(request,"Authorization");
        String authorization = request.getHeader("Authorization");
        System.out.println("token：" + token);
        System.out.println("authorization：" + authorization);
        System.out.println("url："+ request.getRequestURI());
        authorization = "313131jkjuoewqieqwoieq";
        if(StrUtil.isNotEmpty(token)) {
            //通过token构建用户。
            LoginUser loginUser = buildLoginUserByToken(token);

            if (loginUser != null) {
                //将loginUser注入到security，就代表登录成功了。
                SecurityFrameworkUtils.setLoginUser(loginUser, request);
            }
        }
        //继续执行过滤链
        filterChain.doFilter(request, response);
    }

    private LoginUser buildLoginUserByToken(String token) {
        try {
            //对token进行检验，是否在有效期内
            SystemAccessTokenDO systemAccessTokenDO = tokenService.checkAccessToken(token);
            //根据用户id，获取到用户的相关信息。
            AdminUserDO adminUserDO = adminUserService.getUserById(systemAccessTokenDO.getUserId());
            if (adminUserDO == null) {
                return null;
            }
            //创建LoginUser
            return new LoginUser().setId(adminUserDO.getId()).setHasPermission(adminUserDO.getHasPermission());
        }catch (ServiceException serviceException) {
            // 校验 Token 不通过时，考虑到一些接口是无需登录的，所以直接返回 null 即可
            return null;
        }

    }
}
