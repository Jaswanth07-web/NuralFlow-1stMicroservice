package com.example.businessModelCustomer.SecurityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.businessModelCustomer.entity.Employee;
import com.example.businessModelCustomer.entity.UserCredentials;
import com.example.businessModelCustomer.repository.EmployeeRepository;
import com.example.businessModelCustomer.repository.UserCredentialsRepository;

@Service
public class UserCredentialsService {
	@Autowired
    private UserCredentialsRepository userCredentialsRepository;

    @Autowired
    private EmployeeRepository employeeRepository; // Inject the EmployeeRepository

    // Method to get the role based on username
    public String getRoleByUsername(String username) {
        // First, find the UserCredentials object using the username
        UserCredentials userCredentials = userCredentialsRepository.findByUsername(username);
        
        if (userCredentials != null) {
            // Retrieve the employeeNumber from UserCredentials
            int employeeNumber = userCredentials.getEmployeeNumber();
            
            // Find the Employee entity using the employeeNumber
            Employee employee = employeeRepository.findByEmployeeNumber(employeeNumber);
            
            // Return the role from the Employee entity
            return employee != null ? employee.getRole() : null;
        }
        return null; // Return null if userCredentials is not found
    }
}
