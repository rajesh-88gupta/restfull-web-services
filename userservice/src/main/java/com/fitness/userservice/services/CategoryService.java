package com.fitness.userservice.services;

import com.fitness.userservice.CategoryRepository;
import com.fitness.userservice.SubCategoryRepository;
import com.fitness.userservice.dto.CategoryRequest;
import com.fitness.userservice.dto.CategoryResponse;
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
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;

    public CategoryResponse createCategory(CategoryRequest request) {
        if (categoryRepository.existsByName(request.getName())) {
            throw new RuntimeException("Category already exists");
        }

        Category category = new Category();
        category.setName(request.getName());
        category.setDescription(request.getDescription());

        Category saved = categoryRepository.save(category);

        return mapToResponse(saved);
    }


    public SubCategoryResponse addSubCategory(String categoryId, SubCategoryRequest request) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        SubCategory subCategory = new SubCategory();
        subCategory.setName(request.getName());
        subCategory.setDescription(request.getDescription());
        subCategory.setCategory(category);

        category.getSubCategories().add(subCategory);

        Category savedCategory = categoryRepository.save(category);

        // latest subcategory pick karo
        SubCategory savedSub = savedCategory.getSubCategories()
                .get(savedCategory.getSubCategories().size() - 1);

        return mapToSubCategoryResponse(savedSub);
    }

    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<SubCategoryResponse> getSubCategoriesByCategoryId(String categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        return category.getSubCategories()
                .stream()
                .map(this::mapToSubCategoryResponse)
                .collect(Collectors.toList());
    }

    public CategoryResponse getCategory(String id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return mapToResponse(category);
    }

    public CategoryResponse updateCategory(String id, CategoryRequest request) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        category.setName(request.getName());
        category.setDescription(request.getDescription());

        Category updated = categoryRepository.save(category);
        return mapToResponse(updated);
    }

    public void deleteCategory(String id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        categoryRepository.delete(category);
    }

    //    private CategoryResponse mapToResponse(Category category) {
//        CategoryResponse response = new CategoryResponse();
//        response.setId(category.getId());
//        response.setName(category.getName());
//        response.setDescription(category.getDescription());
//
//        // ⭐ Subcategories भी include करो
//        if (category.getSubCategories() != null) {
//            response.setSubCategories(
//                    category.getSubCategories()
//                            .stream()
//                            .map(this::mapToSubCategoryResponse)
//                            .collect(Collectors.toList())
//            );
//        }
//
//        return response;
//    }
    private CategoryResponse mapToResponse(Category category) {
        CategoryResponse response = new CategoryResponse();
        response.setId(category.getId());
        response.setName(category.getName());
        response.setDescription(category.getDescription());

        // ⭐ Subcategories को भी map करो
        if (category.getSubCategories() != null && !category.getSubCategories().isEmpty()) {
            response.setSubCategories(
                    category.getSubCategories()
                            .stream()
                            .map(this::mapToSubCategoryResponse)
                            .collect(Collectors.toList())
            );
        } else {
            response.setSubCategories(List.of()); // empty list instead of null
        }

        return response;
    }

    private SubCategoryResponse mapToSubCategoryResponse(SubCategory subCategory) {
        SubCategoryResponse response = new SubCategoryResponse();
        response.setId(subCategory.getId());
        response.setName(subCategory.getName());
        response.setDescription(subCategory.getDescription());
        response.setCategoryId(subCategory.getCategory().getId());
        return response;
    }

}
