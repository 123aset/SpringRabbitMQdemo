package com.example.rabbitmqex1;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {
    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory("192.168.23.129");
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    //объявляем очередь с именем queue1
    @Bean
    public Queue myQueue1() {
        return new Queue("myQueue1");
    }
    @Bean
    public Queue myQueue2() {
        return new Queue("myQueue2");
    }

    //объявляем контейнер, который будет содержать листенер для сообщений
//    @Bean
//    public SimpleMessageListenerContainer messageListenerContainer1() {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory());
//        container.setQueueNames("queue1");
//        container.setMessageListener(new MessageListener() {
//            //тут ловим сообщения из queue1
//            public void onMessage(Message message) {
//                System.out.println("received from queue1 : " + new String(message.getBody()));
//            }
//        });
//        return container;
//    }

    @Bean
    public FanoutExchange fanoutExchangeA(){
        return new FanoutExchange("exchange-example");
    }

    @Bean
    public Binding binding1(){
        return BindingBuilder.bind(myQueue1()).to(fanoutExchangeA());
    }

    @Bean
    public Binding binding2(){
        return BindingBuilder.bind(myQueue2()).to(fanoutExchangeA());
    }

}