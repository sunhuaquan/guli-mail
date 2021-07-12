package com.sun.mail.member.controller;

import com.sun.mail.member.feign.CouponFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("member/member")
public class MemberController {


    @Autowired
    CouponFeignService couponFeignService;

    @RequestMapping("/coupons")
    public Map<String, Object> test() {
        Map<String, Object> membercoupons = couponFeignService.membercoupons();
        //打印会员和优惠券信息
        return membercoupons;
    }
}

