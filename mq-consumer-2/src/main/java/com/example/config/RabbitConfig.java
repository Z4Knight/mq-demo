package com.example.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: mq-demo
 * @description:
 * @author: Z4Knight
 * @create: 2019-07-16 11:46
 **/
@Configuration
public class RabbitConfig {

    public static final String QUEUE = "demo-queue-2";

    public static final String FANOUT_EXCHANGE = "demo-fanout";

    public static final String DIRECT_EXCHANGE = "demo-direct";

    public static final String HEADER_EXCHANGE = "demo-header";

    @Bean
    Queue queue() {
        return new Queue(QUEUE, false);
    }

    @Bean
    public FanoutExchange fanout() {
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    @Bean
    public DirectExchange direct() {
        return new DirectExchange(DIRECT_EXCHANGE);
    }

    @Bean
    public HeadersExchange header() {
        return new HeadersExchange(HEADER_EXCHANGE);
    }

    @Bean
    public Queue autoDeleteQueue2() {
        return new AnonymousQueue();
    }

    @Bean
    public Queue autoDeleteQueue3() {
        return new AnonymousQueue();
    }

    @Bean
    public Binding binding(Queue autoDeleteQueue2, FanoutExchange fanout) {
        return BindingBuilder.bind(autoDeleteQueue2).to(fanout);
    }

    @Bean
    public Binding binding1a(Queue autoDeleteQueue2, DirectExchange direct) {
        return BindingBuilder.bind(autoDeleteQueue2).to(direct).with("producer2");
    }

    @Bean
    public Binding binding1b(Queue autoDeleteQueue3, HeadersExchange header) {
        Map<String, Object> headers = new HashMap<>(16);
        headers.put("type", "report");
        headers.put("format", "pdf");
        return BindingBuilder.bind(autoDeleteQueue3).to(header).whereAll(headers).match();
    }
}
