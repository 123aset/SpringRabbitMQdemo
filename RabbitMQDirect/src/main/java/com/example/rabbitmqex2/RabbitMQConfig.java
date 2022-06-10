package com.example.rabbitmqex2;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${javainuse.rabbitmq.exchange}")
    String exchange;

    @Bean
    Queue queue1() {
        return new Queue("aset.myQueue1", false);
    }
    @Bean
    Queue queue2() {
        return new Queue("aset.myQueue2", false);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(exchange);
    }

    @Bean
    Binding binding1() {
        return BindingBuilder.bind(queue1()).to(exchange()).with("info");
    }
    @Bean
    Binding binding2() {
        return BindingBuilder.bind(queue2()).to(exchange()).with("warning");
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }


//	@Bean
//	public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
//		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//		rabbitTemplate.setMessageConverter(jsonMessageConverter());
//		return rabbitTemplate;
//	}
}