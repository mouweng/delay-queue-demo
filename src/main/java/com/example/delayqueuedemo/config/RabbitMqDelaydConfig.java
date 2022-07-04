package com.example.delayqueuedemo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMqDelaydConfig {

    // 延时队列交换机,注意这里的交换机类型：CustomExchange
    @Bean
    public CustomExchange delayExchange() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        //属性参数:交换机名称,交换机类型,是否持久化,是否自动删除,配置参数
        return new CustomExchange("delay_exchange", "x-delayed-message", true, false, args);
    }

    // 延时队列
    @Bean
    public Queue delayQueue() {
        //属性参数:队列名称,是否持久化
        return new Queue("delayed.queue", true);
    }

    // 给延时队列绑定交换机
    @Bean
    public Binding cfgDelayBinding() {
        return BindingBuilder.bind(delayQueue()).to(delayExchange()).with("delayed.queue.routingkey").noargs();
    }
}
