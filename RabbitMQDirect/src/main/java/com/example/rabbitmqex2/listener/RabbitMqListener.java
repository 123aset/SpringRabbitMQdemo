package com.example.rabbitmqex2.listener;


import com.example.rabbitmqex2.Employee;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Random;

@EnableRabbit //нужно для активации обработки аннотаций @RabbitListener
@Component
public class RabbitMqListener {
    @RabbitListener(queues = "aset.myQueue1")
    public void worker1(Employee employee) throws InterruptedException {
        System.out.println("1) Get msg: " + employee);
        Thread.sleep(100 * new Random().nextInt(20));
    }

    @RabbitListener(queues = "aset.myQueue2")
    public void worker2(Employee employee) throws InterruptedException {
        System.out.println("2) Get msg: " + employee);
        Thread.sleep(100 * new Random().nextInt(20));
    }
}
