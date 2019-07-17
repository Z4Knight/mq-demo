package com.example.config;

import org.springframework.context.annotation.Configuration;

/**
 * @program: mq-demo
 * @description:
 * @author: Z4Knight
 * @create: 2019-07-16 11:46
 **/
@Configuration
public class RabbitConfig {

    public static final String QUEUE = "demo-queue-1";

    public static final String FANOUT_EXCHANGE = "demo-fanout";

    public static final String DIRECT_EXCHANGE = "demo-direct";

    public static final String TOPIC_EXCHANGE = "demo-topic";

}
