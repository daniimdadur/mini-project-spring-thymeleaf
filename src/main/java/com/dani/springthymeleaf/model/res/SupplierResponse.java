package com.dani.springthymeleaf.model.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierResponse {

    private Long id;
    private String companyName;
    private String contactName;
    private String contactTitle;
    private String address;
    private String city;
}
