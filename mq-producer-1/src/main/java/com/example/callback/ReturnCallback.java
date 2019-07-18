package com.example.callback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @program: mq-demo
 * @description:
 * @author: Z4Knight
 * @create: 2019-07-17 17:09
 **/
@Slf4j
@Component
public class ReturnCallback implements RabbitTemplate.ReturnCallback {
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.info("producer:1 return msg...");
        log.info("replyCode:{}, replyText:{}, exchange:{}, routingKey:{}", replyCode, replyText, exchange, routingKey);
    }
}
