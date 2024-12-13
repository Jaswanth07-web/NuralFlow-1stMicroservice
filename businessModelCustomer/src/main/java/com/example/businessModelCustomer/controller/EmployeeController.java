package com.example.businessModelCustomer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.businessModelCustomer.entity.Employee;
import com.example.businessModelCustomer.exception.SuccessResponse;
import com.example.businessModelCustomer.service.EmployeeService;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/employee")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{employee_no}")
    public ResponseEntity<Employee> getEmployeeByNumber(@PathVariable("employee_no") int employeeNumber) {
        try {
            Employee employee = employeeService.getEmployeeByNumber(employeeNumber);
            return ResponseEntity.ok(employee);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/office/{office_code}")
    public ResponseEntity<List<Employee>> getEmployeesByOffice(@PathVariable("office_code") String officeCode) {
        List<Employee> employees = employeeService.findByOfficeCode(officeCode);
        return employees.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(employees);
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<List<Employee>> getEmployeesByCity(@PathVariable("city") String city) {
        List<Employee> employees = employeeService.getEmployeesByCity(city);
        return employees.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(employees);
    }

    @PostMapping("/add")
    public ResponseEntity<SuccessResponse> addEmployee(@RequestBody Employee employee) {
        employeeService.addEmployee(employee);
        SuccessResponse response = new SuccessResponse(LocalDate.now(), "Employee added successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/update/{employee_number}")
    public ResponseEntity<SuccessResponse> updateEmployee(@PathVariable("employee_number") int employeeNumber, @RequestBody Employee employeeDetails) {
        employeeService.updateEmployee(employeeNumber, employeeDetails);
        SuccessResponse response = new SuccessResponse(LocalDate.now(), "Employee updated successfully");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{oldEmployeeNumber}/reports_to/{newEmployeeNumber}")
    public ResponseEntity<SuccessResponse> updateReportsTo(@PathVariable("oldEmployeeNumber") int oldEmployeeNumber, @PathVariable("newEmployeeNumber") int newEmployeeNumber) {
        employeeService.updateEmployeeReportsTo(oldEmployeeNumber, newEmployeeNumber);
        SuccessResponse response = new SuccessResponse(LocalDate.now(), "Employee reporting structure updated successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{employeeNumber}/role/{role}")
    public ResponseEntity<SuccessResponse> updateEmployeeRole(@PathVariable("employeeNumber") int employeeNumber, @PathVariable("role") String role) {
        employeeService.updateEmployeeRole(employeeNumber,role);
        SuccessResponse response = new SuccessResponse(LocalDate.now(), "Employee role updated successfully");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{employee_number}/map_to_office/{office_code}")
    public ResponseEntity<SuccessResponse> mapEmployeeToOffice(@PathVariable("employee_number") int employeeNumber, @PathVariable("office_code") String officeCode) {
        employeeService.setOfficeDetails(employeeNumber, officeCode);
        SuccessResponse response = new SuccessResponse(LocalDate.now(), "Employee office details updated successfully");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{employee_number}")
    public ResponseEntity<SuccessResponse> deleteEmployee(@PathVariable("employee_number") int employeeNumber) {
        employeeService.deleteEmployee(employeeNumber);
        SuccessResponse response = new SuccessResponse(LocalDate.now(), "Employee deleted successfully");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }
}
