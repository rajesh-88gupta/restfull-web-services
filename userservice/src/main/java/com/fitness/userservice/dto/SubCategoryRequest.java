package com.fitness.userservice.dto;

import lombok.Data;

@Data
public class SubCategoryRequest {
    private String name;
    private String description;
    private String categoryId;
}
