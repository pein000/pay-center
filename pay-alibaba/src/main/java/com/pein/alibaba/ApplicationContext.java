package com.pein.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

/**
 * Created by qiuwei on 2016/8/31.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.pein.alibaba"})
public class ApplicationContext {
    public static void main(String[] args) {
        SpringApplication
                .run(ApplicationContext.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
