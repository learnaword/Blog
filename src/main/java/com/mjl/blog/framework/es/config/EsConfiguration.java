package com.mjl.blog.framework.es.config;

import org.apache.http.HttpHost;
import org.dromara.easyes.starter.register.EsMapperScan;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
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
    @Bean
    public RestHighLevelClient client() {
        return new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")
                )
        );
    }
}
