package com.example.businessModelCustomer.repository;

import java.util.List;
import java.util.Optional;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.businessModelCustomer.entity.Employee;
import com.example.businessModelCustomer.entity.Office;
 
 
//Repository for Offices

@Repository
public interface OfficeRepsoitory extends JpaRepository<Office,String>{
	
//	@Query("Select o from Office o where o.officeCode = :officeCode")
	Office findByOfficeCode(String officeCode);

	@Query("select o.employees from Office o where o.city= :city1 or o.city=:city2")
	List<Employee> findByTwoCites(@Param("city1") String city1,@Param("city2") String city2);
	
	@Query("select o.employees from Office o where o.city= :city1 or o.city=:city2 or o.city=:city3")
	List<Employee> findByThreeCities(@Param("city1") String city1,@Param("city2") String city2,@Param("city3") String city3);

	@Query("select o.city from Office o")
	List<Office> citylist();
	
	List<Office> findByCity(String city);
}