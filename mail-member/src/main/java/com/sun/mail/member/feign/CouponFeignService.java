package com.sun.mail.member.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@FeignClient("mail-coupon")
public interface CouponFeignService {

    @RequestMapping("/coupon/coupon/list")
    Map<String, Object> membercoupons();

}
