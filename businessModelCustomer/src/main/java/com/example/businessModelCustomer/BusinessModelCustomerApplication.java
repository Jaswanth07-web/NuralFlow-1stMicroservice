package com.example.businessModelCustomer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BusinessModelCustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusinessModelCustomerApplication.class, args);
	}

}
