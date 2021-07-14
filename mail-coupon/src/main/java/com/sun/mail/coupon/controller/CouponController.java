package com.sun.mail.coupon.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/coupon")
@RefreshScope
public class CouponController {

    @Value("${coupon.user.name}")
    private String name="";

    @Value("${coupon.user.age}")
    private Integer age;

    @RequestMapping("/coupon/list")
    public Map<String, Object> membercoupons() {
        Map<String, Object> map = new HashMap<>();
        map.put("102011", "满100-10");
        return map;
    }

    @RequestMapping("/test")
    public Map<String, Object> test(HttpServletRequest request){

        int port = request.getServerPort();
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("age", age);
        map.put("port",port);
        return map;

    }
}
