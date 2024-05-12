package com.raiadrogasil.categoryadmin.controller;

import com.raiadrogasil.categoryadmin.dto.CategoryDto;
import com.raiadrogasil.categoryadmin.publisher.RabbitMQProducer;
import com.raiadrogasil.categoryadmin.repository.CategoryRepository;
import com.raiadrogasil.categoryadmin.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/category")
public class Category {

    @Autowired
    public CategoryService categoryService;

    @Autowired
    public RabbitMQProducer rabbitMQProducer;

    public Category(RabbitMQProducer rabbitMQProducer) {
        this.rabbitMQProducer = rabbitMQProducer;
    }

    @PostMapping()
    public ResponseEntity<String> createSkuCategoryBased(@RequestBody List<CategoryDto> categoryDtoList) {

        this.rabbitMQProducer.sendJsonMessage(categoryDtoList);
        return ResponseEntity.ok("Dado enviado para a fila com sucesso");
    }

    @GetMapping(value = "/{sessionToken}")
    public List<CategoryDto> getCategoriesBySessionToken(@PathVariable String sessionToken) {
        return this.categoryService.getCategoriesBySessionToken(sessionToken);
    }

}
