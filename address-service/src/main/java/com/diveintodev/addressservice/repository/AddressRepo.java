package com.diveintodev.addressservice.repository;

import com.diveintodev.addressservice.configuration.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

public interface AddressRepo extends JpaRepository<Address, Integer> {

//@Query(nativeQuery = true, value = "SELECT ea.id, ea.city, ea.state FROM gfgmicroservicesdemo.address ea
// join gfgmicroservicesdemo.employee e on e.id = ea.employee_id where ea.employee_id=:employeeId")
    @Query(
            nativeQuery = true,
            value = "Select a.id,a.flat_no,a.city,a.pincode,a.state,a.employee_id from address.address_table a join employee.employee_table e on e.id = a.employee_id where a.employee_id=:id"
           )
    public Address findAddressByEmployeeId(@Param("id") int id);
}
