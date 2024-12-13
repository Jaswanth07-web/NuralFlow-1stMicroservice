package com.example.businessModelCustomer.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="payments")
@IdClass(PaymentId.class)
public class Payments {
    @Id
    @Column(name = "check_number", length = 50)
    private String checkNumber;
    @Id
    @Column(name = "customer_number",insertable = false, updatable = false)
    private Integer customerNumber;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "customer_number", insertable = false,nullable = false)
    private Customer customers;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date paymentDate;
    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal amount;
    
	public Payments() {
		super();
	}

	public Payments(Integer customerNumber, Customer customers, Date paymentDate, BigDecimal amount) {
		super();
		this.customerNumber = customerNumber;
		this.customers = customers;
		this.paymentDate = paymentDate;
		this.amount = amount;
	}

	public Payments(String checkNumber, Integer customerNumber, Customer customers, Date paymentDate,
			BigDecimal amount) {
		super();
		this.checkNumber = checkNumber;
		this.customerNumber = customerNumber;
		this.customers = customers;
		this.paymentDate = paymentDate;
		this.amount = amount;
	}

	public String getCheckNumber() {
		return checkNumber;
	}

	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}

	public Integer getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(Integer customerNumber) {
		this.customerNumber = customerNumber;
	}

	public Customer getCustomers() {
		return customers;
	}

	public void setCustomers(Customer customers) {
		this.customers = customers;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Payments [checkNumber=" + checkNumber + ", customerNumber=" + customerNumber + ", customers="
				+ customers + ", paymentDate=" + paymentDate + ", amount=" + amount + "]";
	}
	
    
}
