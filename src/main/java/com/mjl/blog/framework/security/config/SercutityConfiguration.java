package com.mjl.blog.framework.security.config;

import com.mjl.blog.framework.MVC.handler.GlobalExceptionHandler;
import com.mjl.blog.framework.security.core.fileter.TokenAuthenticationFilter;
import com.mjl.blog.framework.security.core.handler.AuthenticationEntryPointImpl;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

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

    @Bean
    public TokenAuthenticationFilter authenticationTokenFilter() {
        return new TokenAuthenticationFilter();
    }
}
