package com.dani.springthymeleaf.service;

import com.dani.springthymeleaf.model.req.CategoryRequest;
import com.dani.springthymeleaf.model.res.CategoryResponse;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> getAllCategories();
    CategoryResponse getCategoryById(Long id);
    CategoryResponse addCategory(CategoryRequest categoryRequest);
    CategoryResponse updateCategory(CategoryRequest categoryRequest, Long id);
    CategoryResponse deleteCategory(Long id);
}
