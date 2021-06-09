package com.sun.mall.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MailProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(MailProductApplication.class, args);
    }

}
