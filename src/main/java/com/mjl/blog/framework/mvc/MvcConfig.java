package com.mjl.blog.framework.mvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //用于配置资源处理器（ResourceHandler）以映射 URL 路径到静态资源文件夹
        //将根路径 / 映射到位于 /webapp/ 文件夹中的静态资源。
        registry.addResourceHandler("/")
                .addResourceLocations("/webapp/");
    }

}
