package com.raiadrogasil.categoryadmin.publisher;

import com.raiadrogasil.categoryadmin.dto.CategoryDto;
import com.raiadrogasil.categoryadmin.dto.OrderDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RabbitMQProducer {

    @Value("${rabbitmq.queue.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routingkey.name}")
    private String routingKey;

    @Value("${rabbitmq.order.routingkey.name}")
    private String orderRoutingKey;

    @Autowired
    private final RabbitTemplate rabbitTemplate;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJsonMessage(List<CategoryDto> categoryDto) {
        this.rabbitTemplate.convertAndSend(exchange, routingKey, categoryDto);
    }

    public void sendJsonOrderMessage(OrderDto orderDto) {
        this.rabbitTemplate.convertAndSend(exchange, orderRoutingKey, orderDto);
    }
}
