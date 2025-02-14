package com.diveintodev.employeeservice.service;

import com.diveintodev.employeeservice.FeignnClient;
import com.diveintodev.employeeservice.dto.AddressResponse;
import com.diveintodev.employeeservice.dto.EmployeeResponse;
import com.diveintodev.employeeservice.model.Employee;
import com.diveintodev.employeeservice.repository.EmployeeRepo;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.persistence.Column;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FeignnClient feignClient;

    /*
    private RestClient restClient;

    public EmployeeService(){
        restClient = RestClient.builder()
                .baseUrl("http://ADDRESS-SERVICE/address-app/")
                .build();
    }
    */

    //@Autowired
    //private DiscoveryClient discoveryClient;

    //@Autowired
    //private LoadBalancerClient loadBalancerClient;

    public EmployeeResponse addEmployee(EmployeeResponse employeeResponse) {

        //String url = "http://localhost:8081/address/save-address";

        //Employee employeeData = modelMapper.map(employeeResponse, Employee.class);



        /** Rest call using rest client**/
        /*
        AddressResponse addressResponseBody = restClient.post().uri("/save-address")
                .contentType(MediaType.APPLICATION_JSON)
                .body(address)
                .retrieve()
                .body(AddressResponse.class);
        */

        /** Rest call using feign client**/




        /** Using Discovery client **/
        /*
        List<ServiceInstance> serviceInstances= discoveryClient.getInstances("address-service");
        ServiceInstance serviceInstance = serviceInstances.get(0);
        String uri = serviceInstance.getUri().toString();
        String host = serviceInstance.getHost().toString();
        int port = serviceInstance.getPort();
        System.out.println("uri : " + uri + "  " + "host : " + host + "  " + "port : " + port);
        */

        /** Using LoadBalancer client **/
        /*
        ServiceInstance instance = loadBalancerClient.choose("address-service");
        String uri = instance.getUri().toString();

        String configPath = instance.getMetadata().get("config-path");
        String serviceName = instance.getMetadata().get("service-name");
        System.out.println("serviceName " + serviceName);
        */
        /*AddressResponse addressResponseBody = restClient.post().uri("address/save-address")
                .body(address)
                .retrieve()
                .body(AddressResponse.class);
        */
        Employee emp = new Employee();
        emp.setName(employeeResponse.getName());
        emp.setDesignation(employeeResponse.getDesignation());

        Employee employeeObj = employeeRepo.save(emp);

        AddressResponse address = employeeResponse.getAddressResponse();

        address.setEmp_id(employeeObj.getId());

        ResponseEntity<AddressResponse> addressResponseResponseEntity = feignClient.saveAddressOfEmployee(address);
        AddressResponse addressBody = addressResponseResponseEntity.getBody();

        EmployeeResponse empResponse = new EmployeeResponse();
        empResponse.setName(employeeObj.getName());
        empResponse.setDesignation(employeeObj.getDesignation());
        //empResponse.setAddressResponse(addressBody);
        empResponse.setAddressResponse(addressBody);

        return empResponse;
    }

    int count=1;
    @CircuitBreaker(name="emp-service",fallbackMethod = "getEmployeeDummyById")
    @Retry(name="emp-services",fallbackMethod = "getEmployeeDummyById")
    public EmployeeResponse getEmployeeById(int id) {

        System.out.println("attempt : "+ count++  + "  ===>  " + "At Time : " + new Date());
        LOGGER.info("employee-service : id={}",id);
        Employee employee = employeeRepo.findById(id).get();
        EmployeeResponse employeeResponse =  new EmployeeResponse();
        if(employee !=null){
            AddressResponse addressRes = feignClient.getAddressByEmployeeId(id).getBody();
            employeeResponse.setName(employee.getName());
            employeeResponse.setDesignation(employee.getDesignation());
            employeeResponse.setAddressResponse(addressRes);
        }
        return employeeResponse;
    }

    public EmployeeResponse getEmployeeDummyById(Exception e){
        EmployeeResponse emp = new EmployeeResponse();
            emp.setName("Dummy name");
            emp.setDesignation("Dummy designation");
            emp.setAddressResponse(null);
            return emp;
    }
}
