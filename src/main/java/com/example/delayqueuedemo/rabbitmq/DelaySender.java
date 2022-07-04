package com.example.delayqueuedemo.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
public class DelaySender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void delayedMessage() {
        String context = "test delay message";
        System.out.println("Send time: " + LocalDateTime.now() + "  Send: " + context);
        //延时时间6秒
        rabbitTemplate.convertAndSend("delay_exchange",
                "delayed.queue.routingkey",
                context,
                a -> {
                    // 延迟10s
                    a.getMessageProperties().setDelay(10000);
                    return a;
                });
    }
}
