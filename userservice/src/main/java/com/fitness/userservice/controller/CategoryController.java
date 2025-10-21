package com.fitness.userservice.controller;

import com.fitness.userservice.dto.CategoryRequest;
import com.fitness.userservice.dto.CategoryResponse;
import com.fitness.userservice.dto.SubCategoryRequest;
import com.fitness.userservice.dto.SubCategoryResponse;
import com.fitness.userservice.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryRequest request) {
        return ResponseEntity.ok(categoryService.createCategory(request));
    }

    @PostMapping("/{categoryId}/subcategories")
    public ResponseEntity<SubCategoryResponse> addSubCategory(
            @PathVariable String categoryId,
            @RequestBody SubCategoryRequest request) {
        return ResponseEntity.ok(categoryService.addSubCategory(categoryId, request));
    }

    //    @GetMapping
//    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
//        return ResponseEntity.ok(categoryService.getAllCategories());
//    }
    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{categoryId}/subcategories")
    public ResponseEntity<List<SubCategoryResponse>> getSubCategories(@PathVariable String categoryId) {
        return ResponseEntity.ok(categoryService.getSubCategoriesByCategoryId(categoryId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategory(@PathVariable String id) {
        return ResponseEntity.ok(categoryService.getCategory(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable String id, @RequestBody CategoryRequest request) {
        return ResponseEntity.ok(categoryService.updateCategory(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable String id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

}
