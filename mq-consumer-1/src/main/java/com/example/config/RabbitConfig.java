package com.example.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
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

    @Bean
    public FanoutExchange fanout() {
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    @Bean
    public DirectExchange direct() {
        return new DirectExchange(DIRECT_EXCHANGE);
    }

    @Bean
    public TopicExchange topic() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    Queue queue() {
        return new Queue(QUEUE, false);
    }

    @Bean
    public Queue autoDeleteQueue1() {
        return new AnonymousQueue();
    }

    @Bean
    public Queue autoDeleteQueue1a() {
        return new AnonymousQueue();
    }

    @Bean
    public Binding bindingFanout(Queue autoDeleteQueue1, FanoutExchange fanout) {
        return BindingBuilder.bind(autoDeleteQueue1).to(fanout);
    }

    @Bean
    public Binding bindingDirect(Queue autoDeleteQueue1, DirectExchange direct) {
        return BindingBuilder.bind(autoDeleteQueue1).to(direct).with("producer1");
    }

    @Bean
    public Binding bindingTopic1(Queue autoDeleteQueue1, TopicExchange topic) {
        return BindingBuilder.bind(autoDeleteQueue1).to(topic).with("producer1.*");
    }

    @Bean
    public Binding bindingTopic2(Queue autoDeleteQueue1, TopicExchange topic) {
        return BindingBuilder.bind(autoDeleteQueue1).to(topic).with("#.producer1");
    }
}
