package com.dani.springthymeleaf.service;

import com.dani.springthymeleaf.model.req.ProductRequest;
import com.dani.springthymeleaf.model.res.ProductResponse;

import java.util.List;

public interface ProductService {
    List<ProductResponse> getAll();
    ProductResponse getById(Long id);
    ProductResponse save(ProductRequest request);
    ProductResponse update(ProductRequest request, Long id);
    ProductResponse delete(Long id);
}
