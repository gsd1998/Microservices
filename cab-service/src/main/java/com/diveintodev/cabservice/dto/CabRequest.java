package com.diveintodev.cabservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CabRequest {

    private String cabName;
    private String phoneNo;

}

