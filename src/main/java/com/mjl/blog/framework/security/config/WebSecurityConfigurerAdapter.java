package com.mjl.blog.framework.security.config;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.mjl.blog.framework.security.core.fileter.TokenAuthenticationFilter;
import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Map;
import java.util.Set;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * 自定义的 Spring Security 配置适配器实现
 *
 * @author 芋道源码
 */
@Configuration
@EnableMethodSecurity
public class WebSecurityConfigurerAdapter {

    /**
     * 认证失败处理类 Bean
     */
    @Resource
    private AuthenticationEntryPoint authenticationEntryPoint;
    /**
     * 权限不够处理器 Bean
     */
    @Resource
    private AccessDeniedHandler accessDeniedHandler;
    /**
     * Token 认证过滤器 Bean
     */
    @Resource
    private TokenAuthenticationFilter authenticationTokenFilter;

    /**
     * 由于 Spring Security 创建 AuthenticationManager 对象时，没声明 @Bean 注解，导致无法被注入
     * 通过覆写父类的该方法，添加 @Bean 注解，解决该问题
     */
    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    //在filterChain里使用mvc.pattern的时候需要用到，所以必须得有。
    @Bean
    MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
        return new MvcRequestMatcher.Builder(introspector);
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity httpSecurity,MvcRequestMatcher.Builder mvc) throws Exception {
        // 登出
        httpSecurity
                // 开启跨域
                .cors(withDefaults())
                // CSRF 禁用，因为不使用 Session
                .csrf(csrf -> csrf.disable())
                // 基于 token 机制，所以不需要 Session
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()))
                // 一堆自定义的 Spring Security 处理器
                .exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(authenticationEntryPoint)
                        .accessDeniedHandler(accessDeniedHandler));

        // 登录、登录暂时不使用 Spring Security 的拓展点，主要考虑一方面拓展多用户、多种登录方式相对复杂，一方面用户的学习成本较高

        // 设置每个请求的权限
        httpSecurity
                // ①：全局共享规则
                .authorizeHttpRequests((authz)->authz
                        // 1.1 静态资源，可匿名访问
                        .requestMatchers(mvc.pattern("/admin/login"),mvc.pattern("/admin/checkLogin")).permitAll()
                        .requestMatchers(mvc.pattern("/admin/**")).authenticated()
                        .anyRequest().permitAll()

                );

        // 添加 Token Filter
        httpSecurity.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

}
