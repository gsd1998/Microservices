package com.diveintodev.cabservice.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CabResponse {

    private String cabName;
    private String phoneNo;
    private String employeeName;
    private int employee_id;
}

