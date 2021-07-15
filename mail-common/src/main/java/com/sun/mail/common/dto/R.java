package com.sun.mail.common.dto;

import java.util.HashMap;

public class R extends HashMap<String, Object> {//R继承了HashMap

    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    public static R error(String msg){
        R r = new R();
        r.put("msg", msg);
        return r;
    }
}
