package com.diveintodev.cabservice.controller;

import com.diveintodev.cabservice.dto.CabRequest;
import com.diveintodev.cabservice.dto.CabResponse;
import com.diveintodev.cabservice.dto.EmployeeResponse;
import com.diveintodev.cabservice.service.CabAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cab-service")
public class CabController {

    @Autowired
    private CabAppService cabAppService;

    @PostMapping("/assign-cab/{id}")
    public ResponseEntity<CabResponse> assignCabToEmployee(@PathVariable int id, @RequestBody CabRequest cabRequest){
        CabResponse cabResponse = cabAppService.assignCabToEmployee(id, cabRequest);
        return ResponseEntity.status(HttpStatus.OK).body(cabResponse);
    }
}
