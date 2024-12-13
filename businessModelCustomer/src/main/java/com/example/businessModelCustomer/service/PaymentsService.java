package com.example.businessModelCustomer.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
 
import org.springframework.http.ResponseEntity;

import com.example.businessModelCustomer.entity.Customer;
import com.example.businessModelCustomer.entity.Payments;
 
 
public interface PaymentsService {
	
public List<Payments> getallpayments();
	
	public List<Payments> getByCustomerNumber(int customerNumber);
	
	public List<Payments> getByPaymentDate(Date paymentDate);
	
	public List<Customer> getCustomerByPaymentDate(String paymentDate);
	
	public Payments getallByCheckNumber(String checkNumber);
	
	public BigDecimal getTotalAmountByCustomerNumber(int CustomerNumber);
	
	public Customer GetCustomerByCheckNumber(String CheckNumber);
	
	public Customer getCustomerByMaxAmounts(BigDecimal amount);
	
	public List<Customer> GetCustomerBetweenDates(String StartingDate,String EndDate);
	
	public Payments addPayment(Payments payment);
			
	public void updateAmountByCheckNumber(BigDecimal amount,String CheckNumber);
	
	public void addPatment(Payments payment);

	void updateCheckNumber(String CheckNumber,int customerNumber,String newcheckNumber);

}
