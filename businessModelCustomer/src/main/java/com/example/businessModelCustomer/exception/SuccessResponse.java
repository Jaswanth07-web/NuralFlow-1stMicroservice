package com.example.businessModelCustomer.exception;

import java.time.LocalDate;

public class SuccessResponse {

	private LocalDate timestamp;
	private String message;
	public SuccessResponse(LocalDate timestamp, String message) {
		super();
		this.timestamp = timestamp;
		this.message = message;
	}
	public SuccessResponse() {
		super();
	}
	public LocalDate getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDate timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "SuccessResponse [timestamp=" + timestamp + ", message=" + message + "]";
	}
	
}
