package com.dani.springthymeleaf.service.impl;

import com.dani.springthymeleaf.model.req.SupplierRequest;
import com.dani.springthymeleaf.model.res.SupplierResponse;
import com.dani.springthymeleaf.service.SupplierService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    private List<SupplierResponse> supplier;

    public SupplierServiceImpl(){
        supplier = new ArrayList<SupplierResponse>();

    }
    @Override
    public List<SupplierResponse> getAllSuppliers() {
        List<SupplierResponse> responses = new ArrayList<>();
//        responses.add(new SupplierResponse(1L, "Gentara", "0851111", "Manager", "Pamarican", "Ciamis"));
//        responses.add(new SupplierResponse(2L, "Gulaku", "0852222", "Direktur", "Pamarican", "Ciamis"));
//        responses.add(new SupplierResponse(3L, "Indofood", "0853333", "CEO", "Tanah Abang", "Jakarta"));
        if (this.supplier.isEmpty()){
            this.supplier.addAll(responses);
        }
        return this.supplier;
    }

    @Override
    public SupplierResponse getSupplierById(long id) {
        SupplierResponse response = supplier.stream().filter(Supplier -> Supplier.getId() == id).findFirst().orElse(null);
        return response;
    }

    @Override
    public SupplierResponse addSupplier(SupplierRequest request) {
        SupplierResponse response = new SupplierResponse();
        BeanUtils.copyProperties(request, response);
        setIdAutomatic(response);
        this.supplier.add(response);
        return response;
    }

    @Override
    public SupplierResponse updateSupplier(SupplierRequest request, long id) {
        for (SupplierResponse supplier : this.supplier) {
            if (supplier.getId().equals(id)) {
                BeanUtils.copyProperties(request, supplier);
                return supplier;
            }
        }
        return null;
    }

    @Override
    public SupplierResponse deleteSupplier(long id) {
        SupplierResponse response = new SupplierResponse();
        this.supplier.removeIf(SupplierResponse -> SupplierResponse.getId().equals(id));
        return response;
    }

    private void setIdAutomatic(SupplierResponse response) {
        long maxId = this.supplier.stream().mapToLong(SupplierResponse::getId).max().orElse(0);
        response.setId(maxId + 1);
    }
}
