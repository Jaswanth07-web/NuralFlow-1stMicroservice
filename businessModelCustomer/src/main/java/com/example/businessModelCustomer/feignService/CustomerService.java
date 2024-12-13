package com.example.businessModelCustomer.feignService;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.businessModelCustomer.entity.Customer;
import com.example.businessModelCustomer.exception.SuccessResponse;

@FeignClient(name="customer-service",url="http://localhost:7777")
public interface CustomerService {
	
	//Getting all Customers
		@GetMapping("/api/v1/customer/all")
		public ResponseEntity<List<Customer>> getAll();
		//Getting Customer by customer number
		@GetMapping("/api/v1/customer/{customer_number}")
		public ResponseEntity<Customer> getById(@PathVariable int customer_number);
		//Getting  list of Customer by city
		@GetMapping("/api/v1/customer/city/{city}")
		public ResponseEntity<List<Customer>> getByCity(@PathVariable String city);
		//Getting Customer by customer name
		@GetMapping("/api/v1/customer/customer_name/{customer_name}")
		public ResponseEntity<List<Customer>> getBycustomerName(@PathVariable String customer_name);
		//Getting List of Customers by office Code
		@GetMapping("/api/v1/customer/office/{office_code}")
		public ResponseEntity<List<Customer>> getByOfficeCode(@PathVariable String office_code);
		//Getting List of Customers by salesEmpNumber
		@GetMapping("/api/v1/customer/sales_rep_employee_number/{sales_rep_employee_number}")
		public ResponseEntity<List<Customer>> getBySalesRepEmployeeNumber(@PathVariable int sales_rep_employee_number);
		//Getting List of Customers by country
		@GetMapping("/api/v1/customer/country/{country}")
		public ResponseEntity<List<Customer>> getByCountry(@PathVariable String country);
		//Getting Customer by phone number
		@GetMapping("/api/v1/customer/phone/{phone}")
		public ResponseEntity<Customer> getByPhone(@PathVariable String phone);
		//Getting List of Customers by contact first name
		@GetMapping("/api/v1/customer/contact_firstname/{contact_firstname}")
		public ResponseEntity<List<Customer>> getBycontactFirstName(@PathVariable String contact_firstname);
		// Add new Customer
		@PostMapping("/api/v1/customer/add")
	    public ResponseEntity<SuccessResponse> addCustomer(@RequestBody Customer customer) ;
		// Updating Customer by customer number
		@PutMapping("/api/v1/customer/update/{customer_number}")
	    public ResponseEntity<SuccessResponse> updateCustomer(@PathVariable int customer_number, @RequestBody Customer customer);
		//Updating Customer last_name by customer_number
		@PutMapping("/api/v1/customer/{customer_number}/last/{contact_lastname}")
		public ResponseEntity<SuccessResponse> updateCustomerLastName(@PathVariable int customer_number,@PathVariable String contact_lastname);
		//Updating Customer first_name by customer_number
		@PutMapping("/api/v1/customer/{customer_number}/first/{contact_firstname}")
		public ResponseEntity<SuccessResponse> updateCustomerFirstName(@PathVariable int customer_number,@PathVariable String contact_firstname);
		//Updating address of Customer by customer_number
		@PutMapping("/api/v1/customer/address/{customer_number}")
		public ResponseEntity<SuccessResponse> updateAddress(@PathVariable int customer_number, @RequestBody Customer customer);
		//Deleting Customer by customer_number
		@DeleteMapping("/api/v1/customer/delete/{customer_number}")
		public ResponseEntity<SuccessResponse> deleteCustomer(@PathVariable int customer_number);
		//Get Customer By ContactLastName
	    @GetMapping("/api/v1/customer/contact_lastname/{contact_lastname}")
	    public ResponseEntity<List<Customer>> getCustomersByContactLastName(@PathVariable("contact_lastname") String contactLastName) ;
	    //Get Customer By CreditLimit
	    @GetMapping("/api/v1/customer/credit_limit/{credit_limit}")
	    public ResponseEntity<List<Customer>> getCustomersByCreditLimit(@PathVariable("credit_limit") BigDecimal creditLimit) ;
	    //Get Customer By PostalCode
	    @GetMapping("/api/v1/customer/postal_code/{postal_code}")
	    public ResponseEntity<List<Customer>> getCustomersByPostalCode(@PathVariable("postal_code") String postalCode) ;
	 
	    //Get Customers By CreditRange
	    @GetMapping("/api/v1/customer/credit_range/{min_credit}/{max_credit}")
	    public ResponseEntity<List<Customer>> getCustomersByCreditRange(
	            @PathVariable("min_credit") BigDecimal minCredit,
	            @PathVariable("max_credit") BigDecimal maxCredit) ;
	    
	    //Retrieve all customers where credit limit is Lesserthan
	    @GetMapping("/api/v1/customer/less_than/{credit_limit}")
		public ResponseEntity<List<Customer>> getCustomerslessthancredilimit(@PathVariable("credit_limit") BigDecimal creditlimit);
	    
	    //Retrieve all customers where credit limit is Greaterthan
		@GetMapping("/api/v1/customer/greater_than/{credit_limit}")
		public ResponseEntity<List<Customer>> getCustomersGreaterThanCreditLimit(@PathVariable("credit_limit") BigDecimal creditLimit);
	
}
