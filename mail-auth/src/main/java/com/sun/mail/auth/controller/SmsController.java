package com.sun.mail.auth.controller;


import com.sun.mail.common.dto.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sms")
public class SmsController {


    @Autowired
    private StringRedisTemplate redisTemplate;

    public R sendCode(@RequestParam("phone") String phone) {

        //
        return R.ok("ok");
    }
}
