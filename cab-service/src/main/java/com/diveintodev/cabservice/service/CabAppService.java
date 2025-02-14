package com.diveintodev.cabservice.service;

import com.diveintodev.cabservice.FeignClinet.EmployeeClient;
import com.diveintodev.cabservice.dto.CabRequest;
import com.diveintodev.cabservice.dto.CabResponse;
import com.diveintodev.cabservice.dto.EmployeeResponse;
import com.diveintodev.cabservice.model.Cab;
import com.diveintodev.cabservice.repository.CabRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CabAppService {

    @Autowired
    private EmployeeClient employeeClient;

    @Autowired
    private CabRepository cabRepository;

    public CabResponse assignCabToEmployee(int id, CabRequest cabRequest) {

        ResponseEntity<EmployeeResponse> employeeById = employeeClient.getEmployeeById(id);
        EmployeeResponse employeeDetails = employeeById.getBody();

        CabResponse cabDetails = new CabResponse();

        if(id == employeeDetails.getAddressResponse().getEmp_id()) {
            cabDetails.setCabName(cabRequest.getCabName());
            cabDetails.setPhoneNo(cabRequest.getPhoneNo());
            cabDetails.setEmployeeName(employeeDetails.getName());
            cabDetails.setEmployee_id(employeeDetails.getAddressResponse().getEmp_id());

            Cab cab = new Cab();
            cab.setCabName(cabRequest.getCabName());
            cab.setPhoneNo(cabRequest.getPhoneNo());
            cab.setEmployee_id(id);

            cabRepository.save(cab);
        }
        return cabDetails;
    }
}
