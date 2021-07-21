package com.sun.mail.produce;

import com.sun.mail.produce.servcie.SkuInfoService;
import com.sun.mail.produce.vo.SkuItemVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MailProduceApplicationTests {


    @Autowired
    private SkuInfoService skuInfoService;

    @Test
    void contextLoads() {
    }


    @Test
    public void test() throws Exception {

        SkuItemVo item = skuInfoService.item(1000L);

        System.out.println(item);
    }
}
