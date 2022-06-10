package com.example.rabbitmqex2;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping
public class RabbitMQWebController {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${javainuse.rabbitmq.exchange}")
    private String exchange;

    @GetMapping
    public String producer(
            @RequestParam("empName") String empName,
            @RequestParam("empId") String empId,
            @RequestParam("routing") String routing
    ) {

        Employee emp = new Employee();
        emp.setEmpId(empId);
        emp.setEmpName(empName);
        rabbitTemplate.convertAndSend(exchange, routing, emp);

        return "Message sent to the RabbitMQ JavaInUse Successfully";
    }
}