package com.example.businessModelCustomer.entity;
 
import java.math.BigDecimal;
import java.util.List;

import com.example.businessModelCustomer.VO.OrdersVO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
 

@Entity@Table(name="customers")
public class Customer {
	@Id
	private int customerNumber;
	@Column(length = 50, nullable = false)
	private String customerName;
	@Column(length = 50, nullable = false)
	private String contactLastName;
	@Column(length = 50, nullable = false)
	private String contactFirstName;
	@Column(length = 50, nullable = false)
	private String phone;
	@Column(length = 50, nullable = false)
	private String addressLine1;
	@Column(length = 50)
	private String addressLine2;
	@Column(length = 50, nullable = false)
	private String city;
	@Column(length = 50)
	private String state;
	@Column(length = 15)
	private String postalcode;
	@Column(length = 50, nullable = false)
	private String country;
	@Column(precision = 10,scale=2)
	private  BigDecimal creditLimit;
	@ManyToOne
	@JoinColumn(name="salesRepEmployeeNumber",referencedColumnName = "employeeNumber")
	@JsonBackReference("employee-customer")
	private Employee emp;
	@OneToMany(mappedBy = "customers", cascade = CascadeType.ALL)
	//@JsonManagedReference("customer-payments")
	@JsonIgnore
	private List<Payments> payments;
	public Customer() {
		super();
	}


public Customer(String customerName, String contactLastName, String contactFirstName, String phone,
			String addressLine1, String addressLine2, String city, String state, String postalcode, String country,
			BigDecimal creditLimit, Employee emp, List<Payments> payments/*, List<OrdersVO> orders*/) {
		super();
		this.customerName = customerName;
		this.contactLastName = contactLastName;
		this.contactFirstName = contactFirstName;
		this.phone = phone;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.state = state;
		this.postalcode = postalcode;
		this.country = country;
		this.creditLimit = creditLimit;
		this.emp = emp;
		this.payments = payments;
		//this.orders = orders;
	}



public Customer(int customerNumber, String customerName, String contactLastName, String contactFirstName,
			String phone, String addressLine1, String addressLine2, String city, String state, String postalcode,
			String country, BigDecimal creditLimit, Employee emp, List<Payments> payments/*, List<OrdersVO> orders*/) {
		super();
		this.customerNumber = customerNumber;
		this.customerName = customerName;
		this.contactLastName = contactLastName;
		this.contactFirstName = contactFirstName;
		this.phone = phone;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.state = state;
		this.postalcode = postalcode;
		this.country = country;
		this.creditLimit = creditLimit;
		this.emp = emp;
		this.payments = payments;
		//this.orders = orders;
	}



public int getCustomerNumber() {
	return customerNumber;
}

public void setCustomerNumber(int customerNumber) {
	this.customerNumber = customerNumber;
}

public String getCustomerName() {
	return customerName;
}

public void setCustomerName(String customerName) {
	this.customerName = customerName;
}

public String getContactLastName() {
	return contactLastName;
}

public void setContactLastName(String contactLastName) {
	this.contactLastName = contactLastName;
}

public String getContactFirstName() {
	return contactFirstName;
}

public void setContactFirstName(String contactFirstName) {
	this.contactFirstName = contactFirstName;
}

public String getPhone() {
	return phone;
}

public void setPhone(String phone) {
	this.phone = phone;
}

public String getAddressLine1() {
	return addressLine1;
}

public void setAddressLine1(String addressLine1) {
	this.addressLine1 = addressLine1;
}

public String getAddressLine2() {
	return addressLine2;
}

public void setAddressLine2(String addressLine2) {
	this.addressLine2 = addressLine2;
}

public String getCity() {
	return city;
}

public void setCity(String city) {
	this.city = city;
}

public String getState() {
	return state;
}

public void setState(String state) {
	this.state = state;
}

public String getPostalcode() {
	return postalcode;
}

public void setPostalcode(String postalcode) {
	this.postalcode = postalcode;
}

public String getCountry() {
	return country;
}

public void setCountry(String country) {
	this.country = country;
}

public BigDecimal getCreditLimit() {
	return creditLimit;
}

public void setCreditLimit(BigDecimal creditLimit) {
	this.creditLimit = creditLimit;
}

//public List<OrdersVO> getOrders() {
//	return orders;
//}
//
//public void setOrders(List<OrdersVO> orders) {
//	this.orders = orders;
//}


public Employee getEmp() {
	return emp;
}


public void setEmp(Employee emp) {
	this.emp = emp;
}


public List<Payments> getPayments() {
	return payments;
}


public void setPayments(List<Payments> payments) {
	this.payments = payments;
}


@Override
public String toString() {
	return "Customer [customerNumber=" + customerNumber + ", customerName=" + customerName + ", contactLastName="
			+ contactLastName + ", contactFirstName=" + contactFirstName + ", phone=" + phone + ", addressLine1="
			+ addressLine1 + ", addressLine2=" + addressLine2 + ", city=" + city + ", state=" + state + ", postalcode="
			+ postalcode + ", country=" + country + ", creditLimit=" + creditLimit + ", emp=" + emp + ", payments="
			+ payments /*+ ", orders=" + orders */+ "]";
}




	}