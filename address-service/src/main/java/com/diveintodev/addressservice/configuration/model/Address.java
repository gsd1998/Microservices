package com.diveintodev.addressservice.configuration.model;

import com.diveintodev.addressservice.dto.EmployeeResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address_table")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int flatNo;
    private String city;
    private String state;
    private String pincode;
    @Column(name="employee_id")
    private int emp_id;

}

