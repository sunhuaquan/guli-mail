package com.sun.mail.coupon.listener;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.AbstractMessageListenerContainer;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@Configuration
public class Comsumerlistener {

    /**
     * MANUAL   当每一批poll()的数据被消费者监听器（ListenerConsumer）处理之后, 手动调用Acknowledgment.acknowledge()后提交
     * @param consumerFactory
     * @return
     */
    @Bean("manualListenerContainerFactory")
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> manualListenerContainerFactory(
            ConsumerFactory<String, String> consumerFactory) {

        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.getContainerProperties().setPollTimeout(1500);
        factory.setBatchListener(true);
        //配置手动提交offset
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
        return factory;
    }


    @KafkaListener(containerFactory = "manualListenerContainerFactory", topics = "hello")
    public void onMessage(List<Object> message, Acknowledgment ack){
        log.info("manualListenerContainerFactory 处理数据量：{}",message.size());
        message.forEach(item -> log.info("manualListenerContainerFactory 处理数据内容：{}",item));
        ack.acknowledge();//直接提交offset
    }

}
