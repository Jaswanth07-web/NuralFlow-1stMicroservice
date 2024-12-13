package com.example.businessModelCustomer.exception;

public class OfficeNotFoundException extends RuntimeException {
    public OfficeNotFoundException(String message) {
        super(message);
    }
}