package com.diveintodev.addressservice.controller;


import com.diveintodev.addressservice.dto.AddressResponse;
import com.diveintodev.addressservice.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService service;

    @PostMapping("/save-address")
    public ResponseEntity<AddressResponse> saveAddressOfEmployee(@RequestBody AddressResponse addressResponse){
        System.out.println("I am handling the request");
        AddressResponse savedAddress = service.saveAddress(addressResponse);
        return ResponseEntity.status(HttpStatus.OK).body(savedAddress);
    }

    @GetMapping("/get-address-by-id/{id}")
    public ResponseEntity<AddressResponse> getAddressByEmployeeId(@PathVariable int id){
        System.out.println("I am handling the request");
        AddressResponse addressResponse = service.getAddress(id);
        return ResponseEntity.status(HttpStatus.OK).body(addressResponse);
    }

}
