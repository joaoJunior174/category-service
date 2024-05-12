package com.raiadrogasil.categoryadmin.consumer;

import com.raiadrogasil.categoryadmin.dto.CategoryDto;
import com.raiadrogasil.categoryadmin.service.CategoryService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RabbitMQConsumer {

    @Autowired
    public CategoryService categoryService;

    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void ConsumeJsonMessage(List<CategoryDto> categoryDtoList) {
        this.categoryService.saveAll(categoryDtoList);
    }
}
