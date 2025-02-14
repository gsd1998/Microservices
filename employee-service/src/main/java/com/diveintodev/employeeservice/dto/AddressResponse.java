package com.diveintodev.employeeservice.dto;


import com.diveintodev.employeeservice.model.Employee;
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
