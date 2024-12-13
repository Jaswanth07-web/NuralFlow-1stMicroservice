package com.example.businessModelCustomer.entity;

public class LoginRequest {

    private String username;  // The username provided by the user
    private String password;  // The password provided by the user

    // Default constructor (necessary for deserialization)
    public LoginRequest() {}

    // Constructor to initialize the fields
    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
