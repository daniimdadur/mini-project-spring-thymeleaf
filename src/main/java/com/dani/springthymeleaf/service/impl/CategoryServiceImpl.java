package com.dani.springthymeleaf.service.impl;

import com.dani.springthymeleaf.model.req.CategoryRequest;
import com.dani.springthymeleaf.model.res.CategoryResponse;
import com.dani.springthymeleaf.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    private List<CategoryResponse> categories;

    public CategoryServiceImpl() {
        categories = new ArrayList<>();
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        List<CategoryResponse> responses = new ArrayList<>();
        if (categories.isEmpty()){
            categories.addAll(responses);
        }
        return categories;
    }

    @Override
    public CategoryResponse getCategoryById(Long id) {
        CategoryResponse categoryResponse = categories.stream().filter(category -> category.getId().equals(id)).findFirst().orElse(null);
        return categoryResponse;
    }

    @Override
    public CategoryResponse addCategory(CategoryRequest categoryRequest) {
        CategoryResponse response = new CategoryResponse();
        BeanUtils.copyProperties(categoryRequest, response);
        autoId(response);
        categories.add(response);
        return response;
    }

    @Override
    public CategoryResponse updateCategory(CategoryRequest categoryRequest, Long id) {
        for (CategoryResponse category : this.categories) {
            if (category.getId().equals(id)) {
                BeanUtils.copyProperties(categoryRequest, category);
                return category;
            }
        }
        return null;
    }

    @Override
    public CategoryResponse deleteCategory(Long id) {
        CategoryResponse response = new CategoryResponse();
        categories.removeIf(category -> category.getId().equals(id));
        return response;
    }
    //method untuk membuat id secara automatic
    private void autoId(CategoryResponse response) {
        long maxId = this.categories.stream().mapToLong(CategoryResponse::getId).max().orElse(0);
        response.setId(maxId + 1);
    }
}
