package com.fitness.userservice.services;

import com.fitness.userservice.CategoryRepository;
import com.fitness.userservice.ProductRepository;
import com.fitness.userservice.SubCategoryRepository;
import com.fitness.userservice.dto.ProductRequest;
import com.fitness.userservice.dto.ProductResponse;
import com.fitness.userservice.models.Category;
import com.fitness.userservice.models.Product;
import com.fitness.userservice.models.SubCategory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final SubCategoryRepository categoryRepository;

    public ProductResponse createProduct(ProductRequest request){
        SubCategory category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setSubCategory(category);

        Product saved = productRepository.save(product);
        return mapToResponse(saved);
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public ProductResponse getProduct(String id){
        Product product =productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return mapToResponse(product);
    }

    public ProductResponse updateProduct(String id, ProductRequest request){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        SubCategory category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setSubCategory(category);

        Product updated = productRepository.save(product);
        return mapToResponse(updated);
    }

    public void deleteProduct(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        productRepository.delete(product);
    }

    private ProductResponse mapToResponse(Product product){
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setCategoryId(product.getSubCategory().getId());
        response.setCategoryName(product.getSubCategory().getName());
        return response;
    }
}
