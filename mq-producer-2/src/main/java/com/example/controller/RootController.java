package com.example.controller;

import com.example.bean.Result;
import com.example.config.RabbitConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: mq-controller
 * @description:
 * @author: Z4Knight
 * @create: 2019-07-16 11:02
 **/
@RestController
public class RootController {

    @Autowired
    private RabbitTemplate template;


    @GetMapping("/default")
    public Result send() {
        Result result = new Result();
        result.setMsg("producer:2 send msg...");
        template.convertAndSend(RabbitConfig.QUEUE, result);
        return result;
    }

    @GetMapping("/direct")
    public Result direct() {
        Result result = new Result();
        result.setMsg("producer:2 send msg... by direct");
        template.convertAndSend(RabbitConfig.DIRECT_EXCHANGE, "producer2", result);
        return result;
    }

    @GetMapping("/header")
    public Result header() {
        Result result = new Result();
        result.setMsg("producer:2 send msg... by header");
//        template.convertAndSend(RabbitConfig.HEADER_EXCHANGE, "", result);
        Message message = MessageBuilder.withBody(result.toString().getBytes())
                .setHeader("type", "report")
                .setHeader("format", "pdf")
//                .setHeader("size", 1024)
                .build();
        template.send(RabbitConfig.HEADER_EXCHANGE,"", message);
        return result;
    }
}
