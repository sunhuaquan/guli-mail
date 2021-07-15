package com.sun.mail.member.controller;

import com.sun.mail.common.dto.R;
import com.sun.mail.member.dto.MemberDto;
import com.sun.mail.member.feign.CouponFeignService;
import com.sun.mail.member.valid.AddGroup;
import com.sun.mail.member.valid.UpdateGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("member/member")
public class MemberController {


    @Autowired()
    private CouponFeignService couponFeignService;

    @RequestMapping("/coupons")
    public Map<String, Object> test() {
        Map<String, Object> membercoupons = couponFeignService.membercoupons();
        //打印会员和优惠券信息
        return membercoupons;
    }

    @PostMapping("/addMember")
    public R addMember(@RequestBody @Validated(value = AddGroup.class) MemberDto memberDto) {

        return R.ok("ok");
    }

    @PostMapping("/updateMember")
    public R updateMember(@RequestBody @Validated(value = UpdateGroup.class) MemberDto memberDto) {

        return R.ok("ok");
    }
}


