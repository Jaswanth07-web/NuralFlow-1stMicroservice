package com.example.businessModelCustomer.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.businessModelCustomer.entity.Customer;
import com.example.businessModelCustomer.entity.Payments;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer>{
    
	@Query("select c from Customer c where c.contactLastName = :contactLastName")
	List<Customer> findByContactLastName(@Param("contactLastName") String contactLastName);
	
	@Query("select c from Customer c where c.creditLimit = :creditLimit")
	List<Customer> findByCreditLimit(BigDecimal creditLimit);

	
    @Query("select c from Customer c where c.postalcode= :postalcode")
    List<Customer> findByPostalCode(String postalcode);

  
    @Query("select c from  Customer c where c.creditLimit between :minCredit AND :maxCredit")
    List<Customer> findByCreditLimitRange(@Param("minCredit") BigDecimal minCredit, @Param("maxCredit") BigDecimal maxCredit);
    
    //Searching Customer by Customer_Name
  	List<Customer> findBycustomerName(String name);
  	//Searching Customers by City
  	List<Customer> findByCity(String city);
  	//Searching Customer by Phone
  	Customer findByPhone(String phone);
  	//Searching Customers by Country 
  	List<Customer> findByCountry(String country);
  	//Searching Customer by Contact_First_Name
  	List<Customer> findBycontactFirstName(String name);
  	//Searching Customers by SalesRepEmployeeNumber
  	@Query("SELECT c FROM Customer c WHERE c.emp.employeeNumber= :salesRepEmployeeNumber")
  	List<Customer> findCustomersBySalesRepEmployeeNumber(@Param("salesRepEmployeeNumber") int salesRepEmployeeNumber);
  	//Searching Customers by Office_code 
  	@Query("SELECT c FROM Customer c WHERE c.emp.office.officeCode = :officeCode")
  	List<Customer> findCustomerByOfficeCode(@Param("officeCode") String officeCode);
  	
  	@Query("SELECT c FROM Customer c WHERE c.creditLimit < :creditLimit")
	List<Customer> getcustomerslessthancreditlimit(@Param("creditLimit") BigDecimal creditlimit);
  	
  	@Query("SELECT c FROM Customer c WHERE c.creditLimit > :creditLimit")
	List<Customer> getcustomersgreaterthancreditlimit(@Param("creditLimit") BigDecimal creditlimit);
  	
  	
  	
  	@Query(value="select * from customers where customer_number in (select customer_number from payments where payment_date = CAST(:paymentDate AS date));", nativeQuery = true)
  	public List<Customer> findByCustomerPaymentDate(@Param("paymentDate") String paymentDate);


  	
  	@Query(value = "SELECT * FROM customers WHERE customer_number IN (" +
  	        "    SELECT DISTINCT p.customer_number FROM Payments p " +
  	        "    WHERE p.payment_date BETWEEN CAST(:startPaydate AS DATE) AND CAST(:endPaydate AS DATE));", 
  	        nativeQuery = true)
  	List<Customer> findCustomersByPaymentDateRange(@Param("startPaydate") String startPaydate, 
  	                                               @Param("endPaydate") String endPaydate);

	@Query(value="select * from  Customers where checkNumber= :checkNumber",nativeQuery = true)
	public List<Payments> findByCheckNumbers(@Param("checkNumber") String checkNumber);
	 @Query(value="Select * from customers c where c.customer_number in (select p.customer_number from payments p where p.amount=:amount)",nativeQuery = true )
	 public Customer findCustomerByMaxPayamount(@Param("amount") BigDecimal amount);
}

 

