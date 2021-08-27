package com.sun.mail.test.dubbo.impl;

import com.sun.mail.test.dubbo.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public String hello(String value) {
        return "hello hello"+value;
    }
}
