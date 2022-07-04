package com.example.delayqueuedemo.rabbitmq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class DelayReceiver {
    // 监听消息队列
    @RabbitListener(queues = "delayed.queue")
    public void receive(Message message, Channel channel) throws IOException {
        String s = new String(message.getBody());
        System.out.println("Received time: " + LocalDateTime.now() + "  Received: " + s);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
