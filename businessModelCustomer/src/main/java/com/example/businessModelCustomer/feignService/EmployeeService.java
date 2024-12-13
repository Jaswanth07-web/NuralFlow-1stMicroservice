package com.example.businessModelCustomer.feignService;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.example.businessModelCustomer.entity.Employee;
import com.example.businessModelCustomer.exception.SuccessResponse;

@FeignClient(name="employee-service",url="http://localhost:7777")
public interface EmployeeService {

	@GetMapping("/api/v1/employee/all")
    public ResponseEntity<List<Employee>> getAllEmployees(@RequestHeader("Authorization") String Authorization);
 
    @GetMapping("/api/v1/employee/{employee_no}")
    public ResponseEntity<Employee> getEmployeeByNumber(@PathVariable("employee_no") int employeeNumber);
 
    @GetMapping("/api/v1/employee/office/{office_code}")
    public ResponseEntity<List<Employee>> getEmployeesByOffice(@PathVariable("office_code") String officeCode) ;
 
    @GetMapping("/api/v1/employee/city/{city}")
    public ResponseEntity<List<Employee>> getEmployeesByCity(@PathVariable("city") String city) ;
    @PostMapping("/api/v1/employee/add")
    public ResponseEntity<SuccessResponse> addEmployee(@RequestBody Employee employee) ;
    @PutMapping("/api/v1/employee/update/{employee_number}")
    public ResponseEntity<SuccessResponse> updateEmployee(@PathVariable("employee_number") int employeeNumber, @RequestBody Employee employeeDetails) ;
    @PutMapping("/api/v1/employee/{oldEmployeeNumber}/reports_to/{newEmployeeNumber}")
    public ResponseEntity<SuccessResponse> updateReportsTo(@PathVariable("oldEmployeeNumber") int oldEmployeeNumber, @PathVariable("newEmployeeNumber") int newEmployeeNumber) ;
 
    @PutMapping("/api/v1/employee/{employeeNumber}/role/{role}")
    public ResponseEntity<SuccessResponse> updateEmployeeRole(@PathVariable("employeeNumber") int employeeNumber, @PathVariable("role") String role) ;
    @PutMapping("/api/v1/employee/{employee_number}/map_to_office/{office_code}")
    public ResponseEntity<SuccessResponse> mapEmployeeToOffice(@PathVariable("employee_number") int employeeNumber, @PathVariable("office_code") String officeCode) ;
 
    @DeleteMapping("/api/v1/employee/delete/{employee_number}")
    public ResponseEntity<SuccessResponse> deleteEmployee(@PathVariable("employee_number") int employeeNumber) ;
 
}
