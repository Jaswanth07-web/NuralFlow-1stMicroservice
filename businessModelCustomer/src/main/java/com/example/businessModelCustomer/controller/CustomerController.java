package com.example.businessModelCustomer.controller;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.businessModelCustomer.entity.Customer;
import com.example.businessModelCustomer.exception.SuccessResponse;
import com.example.businessModelCustomer.service.CustomerService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    
	//Getting all Customers
	@GetMapping("/all")
	public ResponseEntity<List<Customer>> getAll(){
		   List<Customer> all = customerService.getAll();
		   return new ResponseEntity<List<Customer>>(all, HttpStatus.OK);
	}
	//Getting Customer by customer number
	@GetMapping("/{customer_number}")
	public ResponseEntity<Customer> getById(@PathVariable int customer_number){
		   Customer all = customerService.getById(customer_number);
		   return new ResponseEntity<Customer>(all, HttpStatus.OK);
	}
	//Getting  list of Customer by city
	@GetMapping("/city/{city}")
	public ResponseEntity<List<Customer>> getByCity(@PathVariable String city){
		   List<Customer> all = customerService.getByCity(city);
		   return new ResponseEntity<List<Customer>>(all, HttpStatus.OK);
	}
	//Getting Customer by customer name
	@GetMapping("/customer_name/{customer_name}")
	public ResponseEntity<List<Customer>> getBycustomerName(@PathVariable String customer_name){
		  List<Customer> all = customerService.getByCustomerName(customer_name);
		   return new ResponseEntity<List<Customer>>(all, HttpStatus.OK);
	}
	//Getting List of Customers by office Code
	@GetMapping("/office/{office_code}")
	public ResponseEntity<List<Customer>> getByOfficeCode(@PathVariable String office_code){
		   List<Customer> all = customerService.getByOffice(office_code);
		   return new ResponseEntity<List<Customer>>(all, HttpStatus.OK);
	}
	//Getting List of Customers by salesEmpNumber
	@GetMapping("/sales_rep_employee_number/{sales_rep_employee_number}")
	public ResponseEntity<List<Customer>> getBySalesRepEmployeeNumber(@PathVariable int sales_rep_employee_number){
		   List<Customer> all = customerService.getBySalesEmpNumber(sales_rep_employee_number);
		   return new ResponseEntity<List<Customer>>(all, HttpStatus.OK);
	}
	//Getting List of Customers by country
	@GetMapping("/country/{country}")
	public ResponseEntity<List<Customer>> getByCountry(@PathVariable String country){
		   List<Customer> all = customerService.getByCountry(country);
		   return new ResponseEntity<List<Customer>>(all, HttpStatus.OK);
	}
	//Getting Customer by phone number
	@GetMapping("/phone/{phone}")
	public ResponseEntity<Customer> getByPhone(@PathVariable String phone){
		   Customer all = customerService.getByPhone(phone);
		   return new ResponseEntity<Customer>(all, HttpStatus.OK);
	}
	//Getting List of Customers by contact first name
	@GetMapping("/contact_firstname/{contact_firstname}")
	public ResponseEntity<List<Customer>> getBycontactFirstName(@PathVariable String contact_firstname){
		   List<Customer> all = customerService.getByContactFirstName(contact_firstname);
		   return new ResponseEntity<List<Customer>>(all, HttpStatus.OK);
	}
	// Add new Customer
	@PostMapping("/add")
    public ResponseEntity<SuccessResponse> addCustomer(@RequestBody Customer customer) {
           customerService.addCustomer(customer);
           return new ResponseEntity<SuccessResponse>(new SuccessResponse(LocalDate.now(),"Customer details added sucessfully"), HttpStatus.CREATED);
    }
	// Updating Customer by customer number
	@PutMapping("/update/{customer_number}")
    public ResponseEntity<SuccessResponse> updateCustomer(@PathVariable int customer_number, @RequestBody Customer customer) {
           customerService.updateCustomer(customer_number, customer);
           return new ResponseEntity<SuccessResponse>(new SuccessResponse(LocalDate.now(),"Customer details updated successfully"), HttpStatus.OK);
    }
	//Updating Customer last_name by customer_number
	@PutMapping("/{customer_number}/last/{contact_lastname}")
	public ResponseEntity<SuccessResponse> updateCustomerLastName(@PathVariable int customer_number,@PathVariable String contact_lastname){
		   customerService.updateCustomerLastName(customer_number, contact_lastname);
		   return new ResponseEntity<SuccessResponse>(new SuccessResponse(LocalDate.now(),"Customer contact lastname Details updated Successfully"), HttpStatus.OK);
	}
	//Updating Customer first_name by customer_number
	@PutMapping("/{customer_number}/first/{contact_firstname}")
	public ResponseEntity<SuccessResponse> updateCustomerFirstName(@PathVariable int customer_number,@PathVariable String contact_firstname){
			customerService.updateCustomerFirstName(customer_number, contact_firstname);
			return new ResponseEntity<SuccessResponse>(new SuccessResponse(LocalDate.now(),"Customer contact firstname Details updated Successfully"),HttpStatus.OK);
		}
	//Updating address of Customer by customer_number
	@PutMapping("/address/{customer_number}")
	public ResponseEntity<SuccessResponse> updateAddress(@PathVariable int customer_number, @RequestBody Customer customer) {   
		    customerService.updateCustomerAddress(customer_number, customer);
	        return new ResponseEntity<SuccessResponse>(new SuccessResponse(LocalDate.now(),"Customer address updated sucessfully"), HttpStatus.OK);
	    }
	//Deleting Customer by customer_number
	@DeleteMapping("/delete/{customer_number}")
	public ResponseEntity<SuccessResponse> deleteCustomer(@PathVariable int customer_number){
		   customerService.deleteByCustomerNumber(customer_number);
		   return new ResponseEntity<>(new SuccessResponse(LocalDate.now(),"Customer deleted successfully"),HttpStatus.ACCEPTED);
	 }

	//Get Customer By ContactLastName
    @GetMapping("/contact_lastname/{contact_lastname}")
    public ResponseEntity<List<Customer>> getCustomersByContactLastName(@PathVariable("contact_lastname") String contactLastName) {
        List<Customer> customers = customerService.getCustomersByContactLastName(contactLastName);
        if (customers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(customers);
    }
    //Get Customer By CreditLimit
    @GetMapping("/credit_limit/{credit_limit}")
    public ResponseEntity<List<Customer>> getCustomersByCreditLimit(@PathVariable("credit_limit") BigDecimal creditLimit) {
        List<Customer> customers = customerService.getCustomersByCreditLimit(creditLimit);
        if (customers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(customers);
    }

    //Get Customer By PostalCode
    @GetMapping("/postal_code/{postal_code}")
    public ResponseEntity<List<Customer>> getCustomersByPostalCode(@PathVariable("postal_code") String postalCode) {
        List<Customer> customers = customerService.getCustomersByPostalCode(postalCode);
        if (customers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(customers);
    }

    //Get Customers By CreditRange
    @GetMapping("/credit_range/{min_credit}/{max_credit}")
    public ResponseEntity<List<Customer>> getCustomersByCreditRange(
            @PathVariable("min_credit") BigDecimal minCredit, 
            @PathVariable("max_credit") BigDecimal maxCredit) {
        List<Customer> customers = customerService.getCustomersByCreditRange(minCredit, maxCredit);
        if (customers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(customers);
    }
    
    //Retrieve all customers where credit limit is Lesserthan 
    @GetMapping("/less_than/{credit_limit}")
	public ResponseEntity<List<Customer>> getCustomerslessthancredilimit(@PathVariable("credit_limit") BigDecimal creditlimit){
		List<Customer> customers=customerService.getcustomerslessthancreditlimit(creditlimit);
		return new ResponseEntity<List<Customer>>(customers,HttpStatus.OK);
	}
    
    //Retrieve all customers where credit limit is Greaterthan 
	@GetMapping("/greater_than/{credit_limit}")
	public ResponseEntity<List<Customer>> getCustomersGreaterThanCreditLimit(@PathVariable("credit_limit") BigDecimal creditLimit){
		List<Customer> customer = customerService.getcustomersgreaterthancreditlimit(creditLimit);
		return new ResponseEntity<List<Customer>>(customer,HttpStatus.OK);
	}
    
}

