package com.sun.mail.test.dubbo.impl;

import com.sun.mail.test.dubbo.DemoService;

public class DemoServiceImpl implements DemoService {
    @Override
    public String test(String value) {
        return "hello "+value;
    }
}
