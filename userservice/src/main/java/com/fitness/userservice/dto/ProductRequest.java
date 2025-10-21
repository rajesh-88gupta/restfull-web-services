package com.fitness.userservice.dto;

import lombok.Data;

@Data
public class ProductRequest {
    private String name;
    private String description;
    private Double price;
    private String categoryId;
}
