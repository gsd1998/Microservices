package com.diveintodev.cabservice.FeignClinet;

import com.diveintodev.cabservice.dto.EmployeeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "employee-service")
public interface EmployeeClient {

    @GetMapping("employee/get-employee/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable int id);

}
