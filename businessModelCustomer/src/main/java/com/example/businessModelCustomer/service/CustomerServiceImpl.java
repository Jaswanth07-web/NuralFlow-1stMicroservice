package com.example.businessModelCustomer.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.businessModelCustomer.entity.Customer;
import com.example.businessModelCustomer.exception.CountryNotFoundException;
import com.example.businessModelCustomer.exception.CustomerAlreadyExistsException;
import com.example.businessModelCustomer.exception.CustomerIdNotFoundException;
import com.example.businessModelCustomer.exception.CustomerNameNotFoundException;
import com.example.businessModelCustomer.exception.CustomerNotFoundByCityException;
import com.example.businessModelCustomer.exception.EmployeeNotFoundException;
import com.example.businessModelCustomer.exception.FieldNotNullException;
import com.example.businessModelCustomer.exception.OfficeNotFoundException;
import com.example.businessModelCustomer.repository.CustomerRepository;
@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
    private CustomerRepository customerRepository;

	@Override
    public List<Customer> getCustomersByContactLastName(String contactLastName) {
        return customerRepository.findByContactLastName(contactLastName);
    }
	
	@Override
    public List<Customer> getCustomersByCreditLimit(BigDecimal creditLimit) {
        return customerRepository.findByCreditLimit(creditLimit);
    }

    @Override
    public List<Customer> getCustomersByPostalCode(String postalCode) {
        return customerRepository.findByPostalCode(postalCode);
    }

    @Override
    public List<Customer> getCustomersByCreditRange(BigDecimal minCredit, BigDecimal maxCredit) {
        return customerRepository.findByCreditLimitRange(minCredit, maxCredit);
    }
    
    
    
	//Get All Customers
	@Override
	public List<Customer> getAll() {
		List<Customer> cust = customerRepository.findAll();
		return cust;
	}
	//Get Customer by customer_number 
	@Override
	public Customer getById(int id) {
		Customer cust = customerRepository.findById(id).orElse(null);
		if(cust==null) {
			throw new CustomerIdNotFoundException("Customer not found by given customer number "+id);
		}
		return cust;
	}
    //Get Customers by office_code 
	@Override
	public List<Customer> getByOffice(String code) {
		List<Customer> all = customerRepository.findCustomerByOfficeCode(code);
		if(all.isEmpty())
			throw new OfficeNotFoundException("Customers are not present for given office code "+code);
		return all;
	}
	//Get Customer by customer_name
	@Override
	public List<Customer> getByCustomerName(String name) {
		List<Customer> cust = customerRepository.findBycustomerName(name);
		if(cust.isEmpty()) {
			throw new CustomerNameNotFoundException("Customers are not present for given customer name "+name);
		}
		return cust;
	}
	//Get Customer by sales_rep_employee_number
		@Override
		public List<Customer> getBySalesEmpNumber(int id) {
	    List<Customer> cust = customerRepository.findCustomersBySalesRepEmployeeNumber(id);
	    if(cust.isEmpty()) {
			throw new EmployeeNotFoundException("Customers are not present for given employee number "+id);
		}
		return cust;
		}
	//Get Customers by city
	@Override
	public List<Customer> getByCity(String city) {
		List<Customer> cust = customerRepository.findByCity(city);
		if(cust.isEmpty()) {
			throw new CustomerNotFoundByCityException("Customers are not present by given city "+city);
		}
		return cust;
	}
 
	//Get Customers by country
	@Override
	public List<Customer> getByCountry(String country) {
		List<Customer> cust = customerRepository.findByCountry(country);
		if(cust.isEmpty()) {
			throw new CountryNotFoundException("Customers are not present by given Country "+country);
		}
		return cust;
	}
	//Get Customers by phone number
	@Override
	public Customer getByPhone(String phone) {
		Customer cust = customerRepository.findByPhone(phone);
		if(cust==null) {
			throw new CustomerIdNotFoundException("Customers are not present by given phone number"+phone);
		}
		return cust;
	}
	//Get Customers by contact_firstname
	@Override
	public List<Customer> getByContactFirstName(String first) {
		List<Customer> cust = customerRepository.findBycontactFirstName(first);
		if(cust.isEmpty()) {
			throw new CustomerNameNotFoundException("Customers are not present for given customer name "+first);
		}
		return cust;
	}
 
	//Add new Customer
	@Override
	public void addCustomer(Customer customer) {
		int id = customer.getCustomerNumber();
		if(customerRepository.existsById(id)) {
			throw new CustomerAlreadyExistsException("Customer is already present by given customer number");
		}
		else if(customer.getAddressLine1()==null||
				customer.getCountry()==null||
				customer.getCustomerName()==null||
				customer.getContactFirstName()==null||
				customer.getContactLastName()==null||
				customer.getCity()==null||
				customer.getPhone()==null) {
			throw new FieldNotNullException("Entered fields should not be null");
		}
		customerRepository.save(customer);
	}
	//Update Customer by customer_number
	@Override
	public void updateCustomer(int customerNumber, Customer customer) {
        Optional<Customer> existingCustomer = customerRepository.findById(customerNumber);
        if (existingCustomer.isPresent()) { 
            customerRepository.save(customer);
        } else {
            throw new CustomerIdNotFoundException("Customer not found by given customer number "+customerNumber);
        }
    }
	//Update Customer’s contact lastname
	@Override
	public void updateCustomerLastName(int customerNumber, String lastName) {
        Optional<Customer> existingCustomer = customerRepository.findById(customerNumber);
        if (existingCustomer.isPresent()) {
            Customer customer = existingCustomer.get();
            customer.setContactLastName(lastName);
            customerRepository.save(customer);
        } else {
            throw new CustomerIdNotFoundException("Customer not found by given customer number "+customerNumber);
        }
    }
	//Update Customer’s contact firstname
	@Override
	public void updateCustomerFirstName(int customerNumber, String firstName) {
		Optional<Customer> existingCustomer = customerRepository.findById(customerNumber);
        if (existingCustomer.isPresent()) {
            Customer customer = existingCustomer.get();
            customer.setContactFirstName(firstName);
            customerRepository.save(customer);
        } else {
            throw new CustomerIdNotFoundException("Customer details not found by given customer number "+customerNumber);
        }
    }
 
	//Update Customer’s address
	@Override
    public void updateCustomerAddress(int customerNumber, Customer customer) {
        Optional<Customer> existingCustomer = customerRepository.findById(customerNumber);
        if (existingCustomer.isPresent()) {
            Customer existing = existingCustomer.get();
            existing.setAddressLine1(customer.getAddressLine1());
            existing.setAddressLine2(customer.getAddressLine2());
            customerRepository.save(existing);
        } else {
            throw new CustomerIdNotFoundException("Customer details not found by given customer number "+customerNumber);
        }
	}
	//Delete Customer by Customer Number
	@Override
	public void deleteByCustomerNumber(int customerNumber) {
		Customer customer = customerRepository.findById(customerNumber).orElse(null);
		if(customer==null) {
			throw new CustomerIdNotFoundException("Customer details by given customer number "+customerNumber);
		}
		customer.setEmp(null);
		customerRepository.save(customer);
		customerRepository.deleteById(customerNumber);
	}
	
	
	@Override
	public List<Customer> getcustomerslessthancreditlimit(BigDecimal creditlimit) {
		// TODO Auto-generated method stub
		List<Customer> customers=customerRepository.getcustomerslessthancreditlimit(creditlimit);
		return customers;
	}
	
	@Override
	public List<Customer> getcustomersgreaterthancreditlimit(BigDecimal creditlimit) {
		return customerRepository.getcustomersgreaterthancreditlimit(creditlimit);
	}
}
