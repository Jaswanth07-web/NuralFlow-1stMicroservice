package com.example.businessModelCustomer.VO;


public final class LoginRequestVO {

    private final String username;  // The username provided by the user
    private final String password;  // The password provided by the user

    // Constructor to initialize the fields
    public LoginRequestVO(String username, String password) {
        if (username == null || password == null) {
            throw new IllegalArgumentException("Username and password cannot be null");
        }
        this.username = username;
        this.password = password;
    }

    // Getters (No setters to maintain immutability)
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // Optionally, you could override `toString()`, `equals()`, and `hashCode()` methods
    @Override
    public String toString() {
        return "LoginRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginRequestVO that = (LoginRequestVO) o;
        return username.equals(that.username) && password.equals(that.password);
    }

    @Override
    public int hashCode() {
        return 31 * username.hashCode() + password.hashCode();
    }
}

