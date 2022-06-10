package com.example.rabbitmqex1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
public class RabbitmqEx1Application {
    /**
     * https://habr.com/ru/post/262069/
     *
     * */
    public static void main(String[] args) {
        SpringApplication.run(RabbitmqEx1Application.class, args);
    }

}
