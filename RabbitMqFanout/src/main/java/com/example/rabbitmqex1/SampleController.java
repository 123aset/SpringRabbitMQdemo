package com.example.rabbitmqex1;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class SampleController {

    private final AmqpTemplate amqpTemplate;
    private final RabbitTemplate rabbitTemplate;

    public SampleController(AmqpTemplate amqpTemplate, RabbitTemplate rabbitTemplate) {
        this.amqpTemplate = amqpTemplate;
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/emit")
    @ResponseBody
    public void queue1(@RequestParam("text") String text) {
        System.out.println("Send to queue1: " + text);
        amqpTemplate.convertAndSend("myQueue1", text);
    }

    @RequestMapping("/emit2")
    @ResponseBody
    public void emit(@RequestParam("text") String text) {
        rabbitTemplate.setExchange("exchange-example");
        rabbitTemplate.convertAndSend(text);
    }
}