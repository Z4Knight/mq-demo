package com.example.controller;


import com.example.bean.Result;
import com.example.config.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        result.setMsg("producer:1 send msg... by default");
        template.convertAndSend(RabbitConfig.QUEUE, result);
        return result;
    }

    @GetMapping("/fanout")
    public Result broadcasts() {
        Result result = new Result();
        result.setMsg("producer:1 send msg... by broadcast");
        template.convertAndSend(RabbitConfig.FANOUT_EXCHANGE, "", result);
        return result;
    }

    @GetMapping("/direct")
    public Result direct() {
        Result result = new Result();
        result.setMsg("producer:1 send msg... by direct");
        template.convertAndSend(RabbitConfig.DIRECT_EXCHANGE, "producer1", result);
        return result;
    }

    @GetMapping("/topic")
    public Result topic(@RequestParam("key") String topicKey) {
        Result result = new Result();
        result.setMsg("producer:1 send msg... by topic " + topicKey);
        template.convertAndSend(RabbitConfig.TOPIC_EXCHANGE, topicKey, result);
        return result;
    }

}
