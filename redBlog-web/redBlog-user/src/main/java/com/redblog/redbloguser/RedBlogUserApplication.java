package com.redblog.redbloguser;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;

@Import(FdfsClientConfig.class)
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)

@EnableEurekaClient
@SpringBootApplication(scanBasePackages = "com.redblog")
@MapperScan(basePackages = "com.redblog.mapper")
public class RedBlogUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedBlogUserApplication.class, args);
    }

}
