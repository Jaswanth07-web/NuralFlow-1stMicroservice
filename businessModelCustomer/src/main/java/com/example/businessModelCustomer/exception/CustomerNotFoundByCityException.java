package com.example.businessModelCustomer.exception;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.management.RuntimeErrorException;


public class CustomerNotFoundByCityException extends RuntimeException {

	public CustomerNotFoundByCityException(String message) {
		super(message);
	}
}
