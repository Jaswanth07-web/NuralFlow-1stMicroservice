package com.example.businessModelCustomer.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


//Entity for Offices

@Entity
@Table(name = "offices")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "officeCode")
public class Office {

	@Id
    @Column(name = "office_code", length = 10, nullable = false)
    private String officeCode;

    @Column(length = 50, nullable = false)
    private String city;

    @Column(length = 50, nullable = false)
    private String phone;

    @Column(name = "address_line1", length = 50, nullable = false)
    private String addressLine1;

    @Column(length = 50)
    private String addressLine2;

    @Column(length = 50)
    private String state;

    @Column(length = 50, nullable = false)
    private String country;

    @Column(name = "postal_code", length = 15, nullable = false)
    private String postalCode;

    @Column(length = 10, nullable = false)
    private String territory;

//    @OneToMany(mappedBy = "office", cascade = CascadeType.ALL)
//    //@JsonIgnore
//    @JsonManagedReference  // Serialize the forward-reference to employees
//    private List<Employee> employees;
    
    
    @OneToMany(mappedBy = "office", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Employee> employees;
    
	public Office() {
		super();
	}



	public Office(String city, String phone, String addressLine1, String addressLine2, String state, String country,
			String postalCode, String territory, List<Employee> employees) {
		super();
		this.city = city;
		this.phone = phone;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.state = state;
		this.country = country;
		this.postalCode = postalCode;
		this.territory = territory;
		this.employees = employees;
	}



	public Office(String officeCode, String city, String phone, String addressLine1, String addressLine2, String state,
			String country, String postalCode, String territory, List<Employee> employees) {
		super();
		this.officeCode = officeCode;
		this.city = city;
		this.phone = phone;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.state = state;
		this.country = country;
		this.postalCode = postalCode;
		this.territory = territory;
		this.employees = employees;
	}



	public String getOfficeCode() {
		return officeCode;
	}



	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
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



	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}



	public String getCountry() {
		return country;
	}



	public void setCountry(String country) {
		this.country = country;
	}



	public String getPostalCode() {
		return postalCode;
	}



	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}



	public String getTerritory() {
		return territory;
	}



	public void setTerritory(String territory) {
		this.territory = territory;
	}



	public List<Employee> getEmployees() {
		return employees;
	}



	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}



	@Override
	public String toString() {
		return "Office [officeCode=" + officeCode + ", city=" + city + ", phone=" + phone + ", addressLine1="
				+ addressLine1 + ", addressLine2=" + addressLine2 + ", state=" + state + ", country=" + country
				+ ", postalCode=" + postalCode + ", territory=" + territory + "]";
	}
    
    
}
