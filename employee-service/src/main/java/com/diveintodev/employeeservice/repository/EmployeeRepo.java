package com.diveintodev.employeeservice.repository;

import com.diveintodev.employeeservice.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee,Integer> {
}
