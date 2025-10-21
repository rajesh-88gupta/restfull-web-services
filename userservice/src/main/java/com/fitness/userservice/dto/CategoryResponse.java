    package com.fitness.userservice.dto;

    import lombok.Data;

    import java.util.List;

    @Data
    public class CategoryResponse {
        private String id;
        private String name;
        private String description;
        private List<SubCategoryResponse> subCategories;
    }
