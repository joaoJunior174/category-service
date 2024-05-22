package com.raiadrogasil.categoryadmin.consumer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raiadrogasil.categoryadmin.dto.CategoryDto;
import com.raiadrogasil.categoryadmin.service.CategoryService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class RabbitMQConsumer {

    @Autowired
    public CategoryService categoryService;

    @Value("${rabbitmq.dlq.exchange.name}")
    private String exchange;

    @Value("${rabbit.dlqRoutingKey.name}")
    private String routingKey;

    @Autowired
    private final RabbitTemplate rabbitTemplate;

    public RabbitMQConsumer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

//    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
//    public void ConsumeJsonMessage(Message message) throws Exception {
//        try {
//            this.categoryService.saveAll(this.convertMessageToDto(message));
//        } catch (Exception e) {
//            this.rabbitTemplate.convertAndSend(exchange, routingKey, this.convertMessageToDto(message));
//        }
//    }

    private List<CategoryDto> convertMessageToDto(Message message) throws Exception {
        String messageBody = new String(message.getBody());
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(messageBody, new TypeReference<List<CategoryDto>>() {});
    }
}


