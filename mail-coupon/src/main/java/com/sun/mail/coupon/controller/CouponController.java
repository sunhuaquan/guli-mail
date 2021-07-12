package com.sun.mail.coupon.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("coupon/coupon")
public class CouponController {

    @RequestMapping("/member/list")
    public Map<String, Object> membercoupons() {
        Map<String, Object> map = new HashMap<>();
        map.put("102011", "满100-10");
        return map;
    }

}
