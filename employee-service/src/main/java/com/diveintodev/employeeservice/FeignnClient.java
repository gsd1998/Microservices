package com.diveintodev.employeeservice;

import com.diveintodev.employeeservice.dto.AddressResponse;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "address-service")//feign client name and eureka instance name should be same inorder to complete the url
public interface FeignnClient {

    @PostMapping("/address/save-address")
    public ResponseEntity<AddressResponse> saveAddressOfEmployee(@RequestBody AddressResponse addressResponse);

    @GetMapping("/address/get-address-by-id/{id}")
    public ResponseEntity<AddressResponse> getAddressByEmployeeId(@PathVariable int id);

}
