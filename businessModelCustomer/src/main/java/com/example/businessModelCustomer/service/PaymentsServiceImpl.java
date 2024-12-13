package com.example.businessModelCustomer.service;
 
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.businessModelCustomer.entity.Customer;
import com.example.businessModelCustomer.entity.Payments;
import com.example.businessModelCustomer.exception.GetListPaymentsException;
import com.example.businessModelCustomer.exception.GetTotalAmountException;
import com.example.businessModelCustomer.exception.NoDateFoundException;
import com.example.businessModelCustomer.exception.PaymentDetailsNotFoundException;
import com.example.businessModelCustomer.repository.CustomerRepository;
import com.example.businessModelCustomer.repository.PaymentsRepository;

import jakarta.transaction.Transactional;

// Payments Service Implementation.
 
@Service
public class PaymentsServiceImpl implements PaymentsService {
	
	@Autowired
    private PaymentsRepository payrepo;
	@Autowired
	private CustomerRepository custRepo;
	
	// Constructor injection for required repositories
    @Autowired
    public PaymentsServiceImpl(PaymentsRepository payrepo, CustomerRepository custRepo) {
        this.payrepo = payrepo;
        this.custRepo = custRepo;
    }
	
    @Override
    public List<Payments> getallpayments() {
       return  payrepo.findAll();
    }
    
	@Override
	public List<Payments> getByCustomerNumber(int customerNumber) {
		List<Payments> ListPaymentsByCustomerNumber=payrepo.findByCustomerNumbers(customerNumber);
		if(ListPaymentsByCustomerNumber.isEmpty()) {
			throw new PaymentDetailsNotFoundException("CustomerNumber Not Found.");
		}
		return ListPaymentsByCustomerNumber;
	}
	
	@Override
	public List<Payments> getByPaymentDate(Date paymentDate) {
		List<Payments> ListPaymentsByPaymentDate=payrepo.findByPaymentDate(paymentDate);
		if(ListPaymentsByPaymentDate.isEmpty()) {
			throw new NoDateFoundException("Paymnets Not found By PaymentDate.");
		}
		return ListPaymentsByPaymentDate;
	}
	
	@Override
	public Payments getallByCheckNumber(String checkNumber) {
		Payments ListPaymentsByCheckNumber=payrepo.findByCheckNumber(checkNumber);
		if(ListPaymentsByCheckNumber==null) {
			throw new GetListPaymentsException("List of Payments Not Found By ChekcNumber.");
		}
		return ListPaymentsByCheckNumber;
	}
	
	@Override
	public List<Customer> getCustomerByPaymentDate(String paymentDate) {	
		List<Customer> customers=custRepo.findByCustomerPaymentDate(paymentDate);
		if(customers.isEmpty()) {
			throw new GetListPaymentsException("CustomerList Not Found Using PaymentDate.");
		}
		return customers;
	}
	
	@Override
	public BigDecimal getTotalAmountByCustomerNumber(int CustomerNumber) {
		 List<Payments> pay=  payrepo.findByCustomerNumbers(CustomerNumber);
		 if(pay.isEmpty()) {
			 throw new GetTotalAmountException("CustomerNumber Not Found.");
		 }	
			BigDecimal total=payrepo.totalAmountByCustomerNumber(CustomerNumber);		
			return total;
	}
	
	public Payments addPayment(Payments payment) {
		return payrepo.save(payment);
	}
	
	@Override
	public Customer getCustomerByMaxAmounts(BigDecimal amount)
	{
		return custRepo.findCustomerByMaxPayamount(amount);
 
	}
	
	@Override
	public Customer GetCustomerByCheckNumber(String CheckNumber) {
		Payments payList=payrepo.findByCheckNumber(CheckNumber);
		Optional<Customer> cus=custRepo.findById(payList.getCustomerNumber());
		return cus.get();
	}
 
	@Override
	public void updateAmountByCheckNumber(BigDecimal amount, String CheckNumber) {
		Payments payment=payrepo.findByCheckNumber(CheckNumber);
		if(payment==null) {
			throw new PaymentDetailsNotFoundException("Payment Details Not Found By CheckNumber.");
		}
		payment.setAmount(amount);
		 payrepo.save(payment);		
	}
	
	@Override
	public List<Customer> GetCustomerBetweenDates(String StartingDate, String EndDate) {
		List<Customer> customersList=custRepo.findCustomersByPaymentDateRange(StartingDate, EndDate);
		if(customersList.isEmpty()) {
			throw new GetListPaymentsException("No Customers Present Between Dates.");
		}
		return customersList;
	}
	
	@Override
	public void addPatment(Payments payment) {
		payrepo.save(payment);		
	}
	
	@Transactional
	public void updateCheckNumber(String CheckNumber, int customerNumber, String newcheckNumber) {      
		payrepo.updateCheckNumber(CheckNumber, customerNumber, newcheckNumber);     }
}


