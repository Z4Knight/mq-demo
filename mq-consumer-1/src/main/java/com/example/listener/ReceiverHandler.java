package com.example.listener;

import com.example.bean.Result;
import com.example.config.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @program: mq-demo
 * @description:
 * @author: Z4Knight
 * @create: 2019-07-16 13:40
 **/
@Component
@Slf4j
public class ReceiverHandler {

    @RabbitListener(queues = {RabbitConfig.QUEUE, "#{autoDeleteQueue1.name}"})
    public void receiver(Result result) {
        log.info("Receiving message: " + result.toString());
    }

}
