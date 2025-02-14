package com.diveintodev.addressservice.service;

import com.diveintodev.addressservice.dto.AddressResponse;
import com.diveintodev.addressservice.configuration.model.Address;
import com.diveintodev.addressservice.repository.AddressRepo;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddressService.class);

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AddressRepo addressRepo;
    public AddressResponse saveAddress(AddressResponse addressResponse) {

        Address addressData = modelMapper.map(addressResponse, Address.class);
        Address addrResponse = addressRepo.save(addressData);
        AddressResponse addressRes = modelMapper.map(addrResponse, AddressResponse.class);

        return addressRes;
    }

    public AddressResponse getAddress(int id) {

        Address address = addressRepo.findAddressByEmployeeId(id);
        LOGGER.info("address service: id={}", id);
        AddressResponse addressData = modelMapper.map(address, AddressResponse.class);

        return addressData;
    }
}
