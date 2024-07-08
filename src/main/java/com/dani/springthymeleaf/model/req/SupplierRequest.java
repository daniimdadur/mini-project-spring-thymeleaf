package com.dani.springthymeleaf.model.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierRequest {
    private Long id;
    private String companyName;
    private String contactName;
    private String contactTitle;
    private String address;
    private String city;
}
