package com.sun.mail.auth;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class MailAuthApplicationTests {


    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    void contextLoads() {
    }


    @Test
    public void test(){

        redisTemplate.opsForValue().set("name","sunhuaquan");
    }

}
