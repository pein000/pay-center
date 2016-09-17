package com.pein.scan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by qiuwei on 2016/9/1.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.pein"})
@MapperScan(basePackages = {"com.pein.repository"})
@EnableTransactionManagement()
public class ApplicationContext extends SpringBootServletInitializer  {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationContext.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(getClass());
    }

}
