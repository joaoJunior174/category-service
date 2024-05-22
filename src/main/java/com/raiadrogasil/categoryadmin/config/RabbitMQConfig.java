package com.raiadrogasil.categoryadmin.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.host}")
    private String host;

    @Value("${rabbitmq.queue.port}")
    private int port;

    @Value("${rabbitmq.queue.username}")
    private String username;

    @Value("${rabbitmq.queue.password}")
    private String password;

    @Value("${rabbitmq.queue.name}")
    private String queue;

    @Value("${rabbitmq.queue.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routingkey.name}")
    private String routingKey;

    @Value("${rabbit.dlqRoutingKey.name}")
    private String dlqRoutingKey;

    @Value("${rabbitmq.dlq.exchange.name}")
    private String dlqExchange;

    @Value("${rabbitmq.dlq.queue.name}")
    private String dlqQueue;

    @Value("${rabbitmq.order.routingkey.name}")
    private String orderRoutingKey;

    @Value("${rabbitmq.queue.order.name}")
    private String orderQueue;

    @Bean
    public Queue queue() {
        return new Queue(queue);
    }

    @Bean
    public Queue orderQueue() {
        return new Queue(orderQueue);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }

    @Bean
    public Binding binding() {
        return BindingBuilder
                .bind(queue())
                .to(exchange())
                .with(routingKey);
    }

    @Bean
    public Binding orderBinding() {
        return BindingBuilder
                .bind(orderQueue())
                .to(exchange())
                .with(orderRoutingKey);
    }

    @Bean
    public Binding bindingDlq() {
        return BindingBuilder
                .bind(dlqQueue())
                .to(dlqExchange())
                .with(dlqRoutingKey);
    }
    @Bean
    public Queue dlqQueue() {
        return new Queue(dlqQueue);
    }

    @Bean
    public DirectExchange dlqExchange() {
        return new DirectExchange(dlqExchange);
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

    private Map<String, Object> createDLQBindingArguments() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", dlqExchange);
        args.put("x-dead-letter-routing-key", dlqRoutingKey);
        return args;
    }


}
