package com.fitness.userservice.dto;

import lombok.Data;

@Data
public class SubCategoryResponse {
    private String id;
    private String name;
    private String description;
    private String categoryId;
    private String categoryName;
}
