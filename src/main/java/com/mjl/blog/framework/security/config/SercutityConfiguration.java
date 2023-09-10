package com.mjl.blog.framework.security.config;

import com.mjl.blog.framework.security.core.fileter.TokenAuthenticationFilter;
import com.mjl.blog.framework.security.core.handler.AuthenticationEntryPointImpl;
import com.mjl.blog.framework.security.core.service.SecurityFrameworkService;
import com.mjl.blog.framework.security.core.service.SecurityFrameworkServiceImpl;
import com.mjl.blog.framework.security.core.handler.AccessDeniedHandlerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
public class SercutityConfiguration {
    /**
     * 认证失败处理类 Bean
     */
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new AuthenticationEntryPointImpl();
    }

    /**
     * 权限不够处理器 Bean
     */
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new AccessDeniedHandlerImpl();
    }

    @Bean("ss") // 使用 Spring Security 的缩写，方便使用
    public SecurityFrameworkService securityFrameworkService() {
        return new SecurityFrameworkServiceImpl();
    }
    @Bean
    public TokenAuthenticationFilter authenticationTokenFilter() {
        return new TokenAuthenticationFilter();
    }
}
