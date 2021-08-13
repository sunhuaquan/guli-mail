package com.sun.mail.member.controller;

import com.sun.mail.common.dto.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    @RequestMapping("/test")
    public R test(String msg) {

        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send("hello", msg);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>(){

            @Override
            public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                log.info("hello - 生产者 发送消息成功：" + stringObjectSendResult.toString());
            }

            @Override
            public void onFailure(Throwable throwable) {
                log.info("wtopic04 - 生产者 发送消息失败：" + throwable.getMessage());
            }
        });
        return R.ok("ok");
    }
}
