package com.sun.mail.coupon.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("coupon/coupon")
@RefreshScope
public class CouponController {

    private String name="";

    @Value("${coupon.user.age}")
    private Integer age;

    @RequestMapping("/member/list")
    public Map<String, Object> membercoupons() {
        Map<String, Object> map = new HashMap<>();
        map.put("102011", "满100-10");
        return map;
    }

    @RequestMapping("/test")
    public Map<String, Object> test(){
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("age", age);
        return map;

    }
}
