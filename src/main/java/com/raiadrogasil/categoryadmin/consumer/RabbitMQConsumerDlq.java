package com.raiadrogasil.categoryadmin.consumer;

import com.raiadrogasil.categoryadmin.dto.CategoryDto;
import com.raiadrogasil.categoryadmin.service.CategoryService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RabbitMQConsumerDlq {

    @Autowired
    private CategoryService categoryService;

    @RabbitListener(queues = "${rabbitmq.dlq.queue.name}")
    public void processFailedMessage(List<CategoryDto> failedMessages) {
        categoryService.saveAllWithNoErrors(failedMessages);
    }
}
