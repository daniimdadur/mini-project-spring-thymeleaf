package com.dani.springthymeleaf.service;

import com.dani.springthymeleaf.model.req.SupplierRequest;
import com.dani.springthymeleaf.model.res.SupplierResponse;

import java.util.List;

public interface SupplierService {

    List<SupplierResponse> getAllSuppliers();
    SupplierResponse getSupplierById(long id);
    SupplierResponse addSupplier(SupplierRequest request);
    SupplierResponse updateSupplier(SupplierRequest request, long id);
    SupplierResponse deleteSupplier(long id);
}
