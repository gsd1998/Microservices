package com.diveintodev.employeeservice.controller;

import com.diveintodev.employeeservice.dto.EmployeeResponse;
import com.diveintodev.employeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @PostMapping("/save-employee")
    public ResponseEntity<EmployeeResponse> saveEmployees(@RequestBody EmployeeResponse employeeResponse){
        EmployeeResponse response = service.addEmployee(employeeResponse);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/get-employee/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable int id, @RequestHeader("username") String username){
        System.out.println("logged in user : " + username);
        EmployeeResponse employeeResponse = service.getEmployeeById(id);
        return ResponseEntity.status(HttpStatus.OK).body(employeeResponse);
    }

}
