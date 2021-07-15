package com.sun.mail.thirdparty;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@SpringBootTest
class ThirdPartyApplicationTests {


    @Autowired
    OSSClient ossClient;

    // Endpoint以杭州为例，其它Region请按实际情况填写。
    @Value("${spring.cloud.alicloud.oss.endpoint}")
    String endpoint ;
    @Value("${spring.cloud.alicloud.access-key}")
    String accessKeyId ;
    @Value("${spring.cloud.alicloud.secret-key}")
    String accessKeySecret ;


    @Test
    public void testUpload() throws FileNotFoundException {

        System.out.println(endpoint+"  "+accessKeyId+" "+accessKeySecret);
        // 创建OSSClient实例。
      /*  OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
*/
        //上传文件流。
        InputStream inputStream = new FileInputStream("C:\\test\\test.jpg");
        ossClient.putObject("saxo-fat", "333.jpg", inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();
        System.out.println("上传成功.");
    }
}
