package com.example.listener;

import com.example.bean.Result;
import com.example.config.RabbitConfig;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

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
    public void receiver(Result result, Channel channel, Message message)  {
        // 默认配置下出现异常，将会反复执行此方法，队列中的消息未丢失，重启消费者将收到消息
        // 设置手动 ack，出现异常将只会执行一次此方法，队列中的消息未丢失，重启消费者将收到消息
        log.info("Receiving message: " + result.toString());
//        throw new NullPointerException();
//        try {
//            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//        } catch (IOException e) {
//            e.printStackTrace();
//            log.info("receiver fail");
//        }
//        log.info("processing complete message: " + result.toString());
    }

}
