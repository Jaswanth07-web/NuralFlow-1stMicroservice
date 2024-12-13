package com.example.businessModelCustomer.feignService;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.businessModelCustomer.entity.Employee;
import com.example.businessModelCustomer.entity.Office;
import com.example.businessModelCustomer.exception.SuccessResponse;

@FeignClient(name="office-service",url="http://localhost:7777")
public interface OfficeService {
	//Retrieve all Offices.
	@GetMapping("/api/v1/office/all")
	public ResponseEntity<List<Office>> getAllOffices();
	//Retrieve Office using officeCode.
	@GetMapping("/api/v1/office/{office_code}")
    public ResponseEntity<Office> findOfficeByOfficeCode(@PathVariable("office_code")String officecode);
	
	//Retrieve Offices which are in these two cities.
	@GetMapping("/api/v1/office/{city1}/{city2}")
	public ResponseEntity<List<Employee>> findOfficesByTwoCities(@PathVariable("city1") String city1,@PathVariable("city2") String city2);
	
	//Retrieve Offices which are in these three cities.
	@GetMapping("/api/v1/office/{city1}/{city2}/{city3}")
	public ResponseEntity<List<Employee>> findOfficesByThreeCities(@PathVariable("city1") String city1,@PathVariable("city2") String city2,@PathVariable("city3") String city3);
	//Insert new Office.
	@PostMapping("/api/v1/office/add")
	public ResponseEntity<SuccessResponse> addNewOffice(@RequestBody Office office);
	//Update Office Phone Number using officeCode.
	@PutMapping("/api/v1/office/{office_code}/{phone_number}")
	public ResponseEntity<SuccessResponse> updatePhoneNumber(@PathVariable("office_code") String officeCode,@PathVariable("phone_number") String phoneNumber);
	//Update Office using officeCode.
	@PutMapping("/api/v1/office/update/{office_code}")
	public ResponseEntity<SuccessResponse> updateOffice(@PathVariable("office_code") String officeCode, @RequestBody Office office);
	//Delete Office using officeCode.
	@DeleteMapping("/api/v1/office/{office_code}")
	public ResponseEntity<SuccessResponse> deleteByOfficeCode(@PathVariable("office_code") String officecode);
}
