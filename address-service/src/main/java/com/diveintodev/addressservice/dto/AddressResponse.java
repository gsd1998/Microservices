package com.diveintodev.addressservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse {

    private int flatNo;
    private String city;
    private String state;
    private String pincode;
    private int emp_id;

}
