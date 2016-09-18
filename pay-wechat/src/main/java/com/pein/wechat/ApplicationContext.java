package com.pein.wechat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by qiuwei on 2016/9/1.
 */
@SpringBootApplication
@ComponentScan(value = {"com.pein.wechat"})
public class ApplicationContext {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationContext.class, args);
    }

}
