package com.redblog.redblogcomm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages = "com.redblog",exclude = DataSourceAutoConfiguration.class)
@EnableEurekaClient
public class RedBlogCommApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedBlogCommApplication.class, args);
	}

}
