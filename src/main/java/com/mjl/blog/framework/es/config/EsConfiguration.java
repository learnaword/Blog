package com.mjl.blog.framework.es.config;

import org.dromara.easyes.starter.register.EsMapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * ES配置
 *
 * @author majunliang
 */
@Configuration
@EsMapperScan("com.mjl.blog.framework.es.mapper.")
@ConditionalOnProperty(value = "easy-es.enable", havingValue = "false")
public class EsConfiguration {
}
