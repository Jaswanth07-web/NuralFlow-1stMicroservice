package com.example.businessModelCustomer.service;

import java.util.List;

import com.example.businessModelCustomer.entity.Employee;


public interface EmployeeService {

    // Get all employees
    List<Employee> getAllEmployees();

    // Get employee by employee number
    Employee getEmployeeByNumber(int employeeNumber);

    // Get employees by office code
    List<Employee> findByOfficeCode(String officeCode);

    // Get employees by city
    List<Employee> getEmployeesByCity(String city);

    // Add a new employee
    Employee addEmployee(Employee employee);

    // Update employee details by employee number
    Employee updateEmployee(int employeeNumber, Employee employeeDetails);

    // Update employee role by employee number
    Employee updateEmployeeRole(int employeeNumber,String role);

    // Delete employee by employee number
    void deleteEmployee(int employeeNumber);

    // Change the reporting structure of an employee
    Employee updateEmployeeReportsTo(int oldEmployeeNumber, int newEmployeeNumber);

    // Set the office details for an employee
    void setOfficeDetails(int employeeNumber, String officeCode);
}
