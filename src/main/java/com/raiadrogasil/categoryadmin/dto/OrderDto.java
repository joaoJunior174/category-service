package com.raiadrogasil.categoryadmin.dto;

import lombok.Data;

@Data
public class OrderDto {
    private String email;
    private String status;
    private Double price;
}

