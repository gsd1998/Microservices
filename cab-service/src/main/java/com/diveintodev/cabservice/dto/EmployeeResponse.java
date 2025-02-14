package com.diveintodev.cabservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {

    private String name;
    private String designation;
    private AddressResponse addressResponse;

}
