package com.fitness.userservice.services;

import com.fitness.userservice.CategoryRepository;
import com.fitness.userservice.SubCategoryRepository;
import com.fitness.userservice.dto.SubCategoryRequest;
import com.fitness.userservice.dto.SubCategoryResponse;
import com.fitness.userservice.models.Category;
import com.fitness.userservice.models.SubCategory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SubCategoryService {
    private final SubCategoryRepository subCategoryRepository;
    private final CategoryRepository categoryRepository;

    public SubCategoryResponse  createSubCategory(SubCategoryRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        SubCategory subCategory = new SubCategory();
        subCategory.setName(request.getName());
        subCategory.setDescription(request.getDescription());
        subCategory.setCategory(category);

        SubCategory saved = subCategoryRepository.save(subCategory);
        return mapToResponse(saved);
    }

    public List<SubCategoryResponse> getSubCategoriesByCategory(String categoryId) {
        return subCategoryRepository.findByCategoryId(categoryId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private SubCategoryResponse mapToResponse(SubCategory subCategory) {
        SubCategoryResponse response = new SubCategoryResponse();
        response.setId(subCategory.getId());
        response.setName(subCategory.getName());
        response.setDescription(subCategory.getDescription());
        response.setCategoryId(subCategory.getCategory().getId());
        response.setCategoryName(subCategory.getCategory().getName());
        return response;
    }

}
