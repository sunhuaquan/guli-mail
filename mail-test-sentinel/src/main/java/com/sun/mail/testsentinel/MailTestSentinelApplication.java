package com.sun.mail.testsentinel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MailTestSentinelApplication {

    public static void main(String[] args) {
        SpringApplication.run(MailTestSentinelApplication.class, args);
    }

}
