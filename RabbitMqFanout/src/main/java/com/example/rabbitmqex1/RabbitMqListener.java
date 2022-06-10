package com.example.rabbitmqex1;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Random;

@EnableRabbit //нужно для активации обработки аннотаций @RabbitListener
@Component
public class RabbitMqListener {
    @RabbitListener(queues = "myQueue1")
    public void worker1(String message) throws InterruptedException {
        System.out.println("worker 1 : " + message);
        Thread.sleep(100 * new Random().nextInt(20));
    }

    @RabbitListener(queues = "myQueue2")
    public void worker2(String message) throws InterruptedException {
        System.out.println("worker 2 : " + message);
        Thread.sleep(100 * new Random().nextInt(20));
    }
}
