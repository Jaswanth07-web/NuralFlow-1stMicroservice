package com.example.businessModelCustomer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.businessModelCustomer.entity.UserCredentials;

@Repository
public interface UserCredentialsRepository extends JpaRepository<UserCredentials, Long> {
    
    // Find a UserCredentials entity by username
    UserCredentials findByUsername(String username);
    
    // Optionally, you can add a method to find by employee number
    UserCredentials findByEmployeeNumber(int employeeNumber);
}

