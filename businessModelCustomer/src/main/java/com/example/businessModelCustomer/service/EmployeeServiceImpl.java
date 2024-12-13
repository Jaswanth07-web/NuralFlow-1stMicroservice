package com.example.businessModelCustomer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.businessModelCustomer.entity.Employee;
import com.example.businessModelCustomer.entity.Office;
import com.example.businessModelCustomer.exception.EmployeeNotFoundException;
import com.example.businessModelCustomer.exception.OfficeNotFoundException;
import com.example.businessModelCustomer.repository.EmployeeRepository;
import com.example.businessModelCustomer.repository.OfficeRepsoitory;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private OfficeRepsoitory officeRepository;

    // Get all employees
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Get employee by employee number
    @Override
    public Employee getEmployeeByNumber(int employeeNumber) {
        return employeeRepository.findById(employeeNumber)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID: " + employeeNumber));
    }

    // Get employees by office code
    @Override
    public List<Employee> findByOfficeCode(String officeCode) {
        List<Employee> employees = employeeRepository.findByOfficeCode(officeCode);
        if (employees.isEmpty()) {
            throw new OfficeNotFoundException("No employees found for office code: " + officeCode);
        }
        return employees;
    }

    // Get employees by city
    @Override
    public List<Employee> getEmployeesByCity(String city) {
        List<Employee> employees = employeeRepository.findByCity(city);
        if (employees.isEmpty()) {
            throw new EmployeeNotFoundException("No employees found in city: " + city);
        }
        return employees;
    }

    // Add a new employee
    @Override
    public Employee addEmployee(Employee employee) {
        if (employeeRepository.existsById(employee.getEmployeeNumber())) {
            throw new EmployeeNotFoundException("Employee with ID " + employee.getEmployeeNumber() + " already exists.");
        }
        return employeeRepository.save(employee);
    }

    // Update employee details by employee number
    @Override
    public Employee updateEmployee(int employeeNumber, Employee employeeDetails) {
        Employee employee = getEmployeeByNumber(employeeNumber);
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmail(employeeDetails.getEmail());
        employee.setJobTitle(employeeDetails.getJobTitle());
        employee.setExtension(employeeDetails.getExtension());
        employee.setOffice(employeeDetails.getOffice());
        employee.setReportsTo(employeeDetails.getReportsTo());
        return employeeRepository.save(employee);
    }

    // Update employee role by employee number
    @Override
    public Employee updateEmployeeRole(int employeeNumber,String role) {
        Employee employee = getEmployeeByNumber(employeeNumber);
        employee.setRole(role);
        return employeeRepository.save(employee);
    }

    // Delete employee by employee number
    @Override
    public void deleteEmployee(int employeeNumber) {
        Employee employee = getEmployeeByNumber(employeeNumber);
        employeeRepository.delete(employee);
    }

    // Change the reporting structure of an employee
    @Override
    public Employee updateEmployeeReportsTo(int oldEmployeeNumber, int newEmployeeNumber) {
        Employee oldEmployee = getEmployeeByNumber(oldEmployeeNumber);
        Employee newManager = getEmployeeByNumber(newEmployeeNumber);
        oldEmployee.setReportsTo(newManager);
        return employeeRepository.save(oldEmployee);
    }

    // Set the office details for an employee
    @Override
    public void setOfficeDetails(int employeeNumber, String officeCode) {
        Employee employee = getEmployeeByNumber(employeeNumber);
        Office office = officeRepository.findByOfficeCode(officeCode);
        if (office == null) {
            throw new OfficeNotFoundException("Office not found with code: " + officeCode);
        }
        employee.setOffice(office);
        employeeRepository.save(employee);
    }
}
