package com.example.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class MyController {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    @RequestMapping("")
    public void send(@RequestParam("text") String text) {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());// 24-часовые часы
        String context = text + " " + date;
        System.out.println("Sender : " + context);
        // RoutingKey - это имя Q в случае простого выравнивания
        this.rabbitTemplate.convertAndSend("myQueue", context);
    }
}