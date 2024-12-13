package com.example.businessModelCustomer.repository;
 
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.businessModelCustomer.entity.PaymentId;
import com.example.businessModelCustomer.entity.Payments;

import jakarta.transaction.Transactional;
 
// Payments Repository.

@Repository
public interface PaymentsRepository extends JpaRepository<Payments,PaymentId>{

	@Query(value="select *from payments where customer_number =:customerNumber", nativeQuery=true)
	public List<Payments> findByCustomerNumbers(@Param("customerNumber") int customerNumber);
	
	@Query(value="select * from  Payments where payment_date= :paymentDate",nativeQuery = true)
	public List<Payments> findByPaymentDate(@Param("paymentDate") Date paymentDate);
	
	@Query(value="select * from  Payments where check_number= :checkNumber",nativeQuery = true)
	public Payments findByCheckNumber(@Param("checkNumber") String checkNumber);
	
	@Query(value="Select coalesce(Sum(p.amount), 0) from Payments p where p.customer_number = :customerNumber",nativeQuery = true)
	public BigDecimal totalAmountByCustomerNumber(@Param("customerNumber") int customerNumber);
	
	public Payments findByCustomerNumber(Integer customerNumber);
	
	@Modifying
	@Transactional
	@Query("UPDATE Payments p SET p.checkNumber = :newCheckNumber WHERE p.checkNumber = :checkNumber AND p.customerNumber = :customerNumber")
	void updateCheckNumber(@Param("checkNumber")String checkNumber,@Param("customerNumber")Integer customerNumber,@Param("newCheckNumber")String newCheckNumber); 

 
 


}
