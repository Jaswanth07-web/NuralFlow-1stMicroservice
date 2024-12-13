package com.example.businessModelCustomer.feignService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.businessModelCustomer.entity.Customer;
import com.example.businessModelCustomer.entity.Payments;


@FeignClient(name="payment-service",url="http://localhost:7777")
public interface PaymentService {
	
	@GetMapping("api/v1/payment/all")
    public ResponseEntity<List<Payments>> getAll();
	
	@GetMapping("api/v1/payment/{customerNumber}")
    public ResponseEntity<List<Payments>> getPayments(@PathVariable("customerNumber") int customerNumber);
	
	@GetMapping("api/v1/payment/paymentDate/{paymentDate}")
    public ResponseEntity<List<Payments>> getPaymentsByPaymentDate(@PathVariable("paymentDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date paymentDate);
	
	@GetMapping("api/v1/payment/customer_payment_date/{paymentdate}")
    public ResponseEntity<List<Customer>> getAllCustomersByPaymentDate(@PathVariable("paymentdate") String paymentDate);
	
	@GetMapping("api/v1/payment/checkNumber/{checkNumber}")
    public ResponseEntity<Payments> getAllPaymentsByCheckNumber(@PathVariable("checkNumber") String checkNumber);
	
	@GetMapping("api/v1/payment/total_amount/{customer_number}")
    public ResponseEntity<BigDecimal> getTotalAmountByCustomerNumber(@PathVariable("customer_number") int customerNumber);
	
	@GetMapping("api/v1/payment/customers/{check_number}")
    public ResponseEntity<Customer> getCustomersByCheckNumber(@PathVariable("check_number") String checkNumber);
	
	@GetMapping("api/v1/payment/max_amount/{amount}")
	public ResponseEntity<Customer> getCustomerByMaxPayamount(@PathVariable("amount") BigDecimal amount);
	
	@GetMapping("api/v1/payment/{startPaydate}/{endpaydate}")  
    public ResponseEntity<List<Customer>> getCustomerByStartingDateEndDate(@PathVariable String startPaydate,@PathVariable String endpaydate);
	
	@PostMapping("api/v1/payment/add")
    public ResponseEntity<Object> addPayment(@RequestBody Payments payment);
	
	@PutMapping("api/v1/payment/{amount}/{check_number}")
    public ResponseEntity<Object> updateAmount(@PathVariable BigDecimal amount,@PathVariable String check_number);
 
}
