package com.dani.springthymeleaf.service.impl;

import com.dani.springthymeleaf.model.req.ProductRequest;
import com.dani.springthymeleaf.model.res.ProductResponse;
import com.dani.springthymeleaf.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private List<ProductResponse> responses;

    public ProductServiceImpl(){
        responses = new ArrayList<>();
    }
    @Override
    public List<ProductResponse> getAll() {
        List<ProductResponse> responses1 = new ArrayList<>();

        if (this.responses.isEmpty()){
            this.responses.addAll(responses1);
        }
        return responses;
    }

    @Override
    public ProductResponse getById(Long id) {
        ProductResponse productResponse = responses.stream().filter(responses -> responses.getId().equals(id)).findFirst().orElse(null);
        return productResponse;
    }

    @Override
    public ProductResponse save(ProductRequest request) {
        ProductResponse response = new ProductResponse();
        BeanUtils.copyProperties(request, response);
        setIdAutomatic(response);
        this.responses.add(response);
        return response;
    }

    @Override
    public ProductResponse update(ProductRequest request, Long id) {
        for (ProductResponse productResponse : this.responses) {
            if (productResponse.getId().equals(id)) {
                BeanUtils.copyProperties(request, productResponse);
                return productResponse;
            }
        }
        return null;
    }

    @Override
    public ProductResponse delete(Long id) {
        ProductResponse response = new ProductResponse();
        this.responses.removeIf(productResponse -> productResponse.getId().equals(id));
        return response;
    }

    //method untuk membuat id automatic
    private void setIdAutomatic(ProductResponse response){
        long maxId = this.responses.stream()
                .mapToLong(ProductResponse::getId)
                .max()
                .orElse(0);
        response.setId(maxId + 1);
    }
}
