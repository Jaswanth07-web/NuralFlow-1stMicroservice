package com.example.businessModelCustomer.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class UserCredentials {

    @Id
    private Long id;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(name = "employee_number", nullable = false, unique = true)
    private int employeeNumber;

    // Default constructor
    public UserCredentials() {
    }

    public UserCredentials(String password, String username, int employeeNumber) {
		super();
		this.password = password;
		this.username = username;
		this.employeeNumber = employeeNumber;
	}
    
    @ManyToOne
    @JoinColumn(name = "employee_number", referencedColumnName = "employeeNumber", insertable = false, updatable = false)
    @JsonBackReference  // Prevent serialization of the back-reference to avoid infinite recursion
    private Employee employee;
    
    // Constructor with fields
    public UserCredentials(Long id, String password, String username, int employeeNumber) {
        this.id = id;
        this.password = password;
        this.username = username;
        this.employeeNumber = employeeNumber;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

	@Override
	public String toString() {
		return "UserCredentials [id=" + id + ", password=" + password + ", username=" + username + ", employeeNumber="
				+ employeeNumber + "]";
	}

    
}

