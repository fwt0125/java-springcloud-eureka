package cn.xiit.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"cn.xiit.springcloud"})
public class DeptConsumer_8085 {
    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer_8085.class, args);
    }
}
