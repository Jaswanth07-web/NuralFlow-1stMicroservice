package com.example.businessModelCustomer.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.businessModelCustomer.entity.Customer;
import com.example.businessModelCustomer.entity.Payments;
import com.example.businessModelCustomer.exception.SuccessResponse;
import com.example.businessModelCustomer.repository.PaymentsRepository;
import com.example.businessModelCustomer.service.PaymentsService;


//  Payments Controller.
@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/payment")  // Add base path for clarity
public class PaymentsController 
{
	@Autowired
    private PaymentsService payservice;
	@Autowired
	private PaymentsRepository payrepo;
		
	// Constructor injection
    @Autowired
    public PaymentsController(PaymentsService payservice, PaymentsRepository payrepo) {
        this.payservice = payservice;
        this.payrepo = payrepo;
    }
// Retrieve All PaymentDetails.
	
    @GetMapping("/all") 
    public ResponseEntity<List<Payments>> getAll() {
        List<Payments> li = payservice.getallpayments();
        if (li == null || li.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(li, HttpStatus.OK);
    }
 
// Retrieve Payments By Using CustomerNumber.
    
    @GetMapping("/{customerNumber}") 
    public ResponseEntity<List<Payments>> getPayments(@PathVariable("customerNumber") int customerNumber) {
        List<Payments> paymentsList = payservice.getByCustomerNumber(customerNumber);
        return new ResponseEntity<>(paymentsList, HttpStatus.OK);
    }
    
// Retrieve PaymentsDetails By Using PaymentDate.
    
    @GetMapping("/paymentDate/{paymentDate}")
    public ResponseEntity<List<Payments>> getPaymentsByPaymentDate(@PathVariable("paymentDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date paymentDate){
    	List<Payments> paymentsList=payservice.getByPaymentDate(paymentDate);
		return new ResponseEntity<List<Payments>>(paymentsList,HttpStatus.OK); 	
    }
    
 // Retrieve  Customers By Using PaymentDate.
    @GetMapping("/customer_payment_date/{paymentdate}") 
    public ResponseEntity<List<Customer>> getAllCustomersByPaymentDate(@PathVariable("paymentdate") String paymentDate){
    	List<Customer> CustomersList=payservice.getCustomerByPaymentDate(paymentDate);
		return new ResponseEntity<List<Customer>>(CustomersList,HttpStatus.OK);  	
    }
 
// Retrieve  Payments By Using CheckNumber.
    
    @GetMapping("/checkNumber/{checkNumber}") 
    public ResponseEntity<Payments> getAllPaymentsByCheckNumber(@PathVariable("checkNumber") String checkNumber){
    	Payments paymentsList=payservice.getallByCheckNumber(checkNumber);
		return new ResponseEntity<>(paymentsList,HttpStatus.OK); 	
    }

 
// Retrieve Total Amount of CustmoerNumber By Using CustomerNumber.
    
    @GetMapping("/total_amount/{customer_number}") 
    public ResponseEntity<BigDecimal> getTotalAmountByCustomerNumber(@PathVariable("customer_number") int customerNumber){
    	BigDecimal totalamount=payservice.getTotalAmountByCustomerNumber(customerNumber);
		return new ResponseEntity<BigDecimal>(totalamount,HttpStatus.OK);    	
    }
    
// Retrieve Customers By Using CheckNumber.
    
    @GetMapping("/customers/{check_number}") 
    public ResponseEntity<Customer> getCustomersByCheckNumber(@PathVariable("check_number") String checkNumber)
    {
    	Customer custList=payservice.GetCustomerByCheckNumber(checkNumber);
    	return new ResponseEntity<>(custList, HttpStatus.OK);
    }
    
// Retrieve Customer By Using Amount.
    
    @GetMapping("/max_amount/{amount}") 
    public ResponseEntity<Customer> getCustomerByMaxPayamount(@PathVariable("amount") BigDecimal amount){
    	Customer ListCustomer=payservice.getCustomerByMaxAmounts(amount);
		return new ResponseEntity<Customer>(ListCustomer,HttpStatus.OK);   	
    }
    
// Retrieve Customers between start payment_date and  end payment_date.
    
    @GetMapping("/{startPaydate}/{endpaydate}")  
    public ResponseEntity<List<Customer>> getCustomerByStartingDateEndDate(@PathVariable String startPaydate,@PathVariable String endpaydate){
    	List<Customer> CustList=payservice.GetCustomerBetweenDates(startPaydate, endpaydate);
		return new ResponseEntity<List<Customer>>(CustList,HttpStatus.OK);
    	
    }
 
// Add New PaymentDetails.
    
    @PostMapping("/add") 
    public ResponseEntity<Object> addPayment(@RequestBody Payments payment){
    	Payments addPayment=payservice.addPayment(payment);
    	SuccessResponse response = new SuccessResponse(LocalDate.now(), "payment details added successfully.");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    	
    }
    
// Update Payment’s Amount By CheckNumber .
    
    @PutMapping("/{amount}/{check_number}") 
    public ResponseEntity<Object> updateAmount(@PathVariable BigDecimal amount,@PathVariable String check_number)
    {
    	payservice.updateAmountByCheckNumber(amount, check_number);
    	SuccessResponse response = new SuccessResponse(LocalDate.now(), "amount updated successfully.");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

// Update CheckNumber By Using CustomerNumber and CheckNumber.
    
    @PutMapping("/check_number/{check_number}/{customer_number}/{new_check_number}")
    public ResponseEntity<Object> updateCheckNumber
    		(@PathVariable("check_number")String checkNumber,@PathVariable("customer_number")Integer customerNumber,@PathVariable("new_check_number")String newCheckNumber) {        
    	   payservice.updateCheckNumber(checkNumber, customerNumber, newCheckNumber);
    	   SuccessResponse response = new SuccessResponse(LocalDate.now(), "check number updated successfully.");
           return ResponseEntity.status(HttpStatus.OK).body(response);
    }   
} 