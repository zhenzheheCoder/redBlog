package com.redblog.redblogfriend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;
@EnableEurekaClient
@EnableFeignClients("com.redblog.service.api")
@SpringBootApplication(scanBasePackages = "com.redblog")
@MapperScan(basePackages = "com.redblog.mapper")
public class RedBlogFriendApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedBlogFriendApplication.class, args);
    }

}
