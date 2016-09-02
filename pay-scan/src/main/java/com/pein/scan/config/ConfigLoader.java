package com.pein.scan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by qiuwei on 2016/9/2.
 */
@Configuration
public class ConfigLoader {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
