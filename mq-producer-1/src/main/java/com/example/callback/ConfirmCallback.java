package com.example.callback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @program: mq-demo
 * @description:
 * @author: Z4Knight
 * @create: 2019-07-17 16:43
 **/
@Component
@Slf4j
public class ConfirmCallback implements RabbitTemplate.ConfirmCallback {

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("producer:1 confirm msg...");
        if (ack) {
            log.info("success");
        } else {
            log.info("error cause is {}", cause);
        }
    }
}
