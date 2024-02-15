package com.redblog.redblogpost;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableEurekaClient
@EnableFeignClients("com.redblog.service.api")
@SpringBootApplication(scanBasePackages = "com.redblog")
@MapperScan(basePackages = "com.redblog.mapper")

public class RedBlogPostApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedBlogPostApplication.class, args);
    }

}
