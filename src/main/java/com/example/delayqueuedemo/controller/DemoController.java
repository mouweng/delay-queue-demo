package com.example.delayqueuedemo.controller;

import com.example.delayqueuedemo.rabbitmq.DelaySender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: wengyifan
 * @description:
 * @date: 2022/7/4 5:14 下午
 */
@RestController
public class DemoController {
    @Autowired
    private DelaySender delaySender;

    @GetMapping("/")
    public String delaySender() {
        delaySender.delayedMessage();
        return "success";
    }
}
