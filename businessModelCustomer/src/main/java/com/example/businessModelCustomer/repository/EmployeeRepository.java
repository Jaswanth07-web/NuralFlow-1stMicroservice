package com.example.businessModelCustomer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.businessModelCustomer.entity.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    
    // Find employees by office code
    @Query("SELECT e FROM Employee e WHERE e.office.officeCode = :officeCode")
    List<Employee> findByOfficeCode(@Param("officeCode") String officeCode);
    
    // Find employees by city
    @Query("SELECT e FROM Employee e WHERE e.office.city = :city")
    List<Employee> findByCity(@Param("city") String city);
    
    // Find employee by employee number
    @Query("SELECT e FROM Employee e WHERE e.employeeNumber = :employeeNumber")
    Employee findByEmployeeNumber(@Param("employeeNumber") int employeeNumber);
    
}
