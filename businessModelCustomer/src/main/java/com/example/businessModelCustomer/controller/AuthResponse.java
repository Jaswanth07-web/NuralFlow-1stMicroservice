package com.example.businessModelCustomer.controller;

public class AuthResponse {

    private String token;  // The JWT token generated upon successful login

    // Default constructor
    public AuthResponse() {}

    // Constructor to initialize the token
    public AuthResponse(String token) {
        this.token = token;
    }

    // Getter and setter for token
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

