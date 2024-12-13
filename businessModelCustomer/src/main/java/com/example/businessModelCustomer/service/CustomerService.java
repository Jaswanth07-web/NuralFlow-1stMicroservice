package com.example.businessModelCustomer.service;

import java.math.BigDecimal;
import java.util.List;

import com.example.businessModelCustomer.entity.Customer;

public interface CustomerService {

	 List<Customer> getCustomersByContactLastName(String contactLastName);
	 
	 List<Customer> getCustomersByCreditLimit(BigDecimal creditLimit);

	 List<Customer> getCustomersByPostalCode(String postalCode);

	 List<Customer> getCustomersByCreditRange(BigDecimal minCredit, BigDecimal maxCredit);
	 
	    List<Customer> getAll();
		Customer getById(int id);
		List<Customer> getByOffice(String code);
		List<Customer> getByCustomerName(String name);
		List<Customer> getBySalesEmpNumber(int id);
		List<Customer> getByCity(String city);
		List<Customer> getByCountry(String country);
		Customer getByPhone(String phone);
		List<Customer> getByContactFirstName(String first);
		void addCustomer(Customer customer); // Add a new customer
	 
	    void updateCustomer(int customerNumber, Customer customer); // Update customer details
	 
	    void updateCustomerLastName(int customerNumber, String lastName); // Update customer's last name
	 
	    void updateCustomerFirstName(int customerNumber, String firstName); // Update customer's first name
	 
	    void updateCustomerAddress(int customerNumber, Customer customer); // Update customer's address details
	    void deleteByCustomerNumber(int customerNumber); //Delete Customer by customer number	

	    List<Customer> getcustomerslessthancreditlimit(BigDecimal creditlimit);
	    
	    
		public List<Customer> getcustomersgreaterthancreditlimit(BigDecimal creditlimit);
			
}
