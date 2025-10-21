package com.fitness.userservice.controller;

import com.fitness.userservice.dto.SubCategoryRequest;
import com.fitness.userservice.dto.SubCategoryResponse;
import com.fitness.userservice.services.SubCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subcategories")
@AllArgsConstructor
public class SubCategoryController {
    private final SubCategoryService subCategoryService;
    @PostMapping
    public ResponseEntity<SubCategoryResponse> createSubCategory(@RequestBody SubCategoryRequest request) {
        return ResponseEntity.ok(subCategoryService.createSubCategory(request));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<SubCategoryResponse>> getSubCategoriesByCategory(@PathVariable String categoryId) {
        return ResponseEntity.ok(subCategoryService.getSubCategoriesByCategory(categoryId));
    }

}
