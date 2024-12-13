package com.example.businessModelCustomer.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.businessModelCustomer.entity.Employee;
import com.example.businessModelCustomer.entity.Office;
import com.example.businessModelCustomer.exception.SuccessResponse;
import com.example.businessModelCustomer.service.OfficeService;
 
 
//Controller for Offices
@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/office")
public class OfficeController {
	@Autowired
	private OfficeService officeservice;
	
	//Retrieve all Offices.
	@GetMapping("/all")
	public ResponseEntity<List<Office>> getAllOffices(){
		List<Office> offices=officeservice.getAllOffices();
		return ResponseEntity.ok(offices);
	}

	//Retrieve Office using officeCode.
	@GetMapping("/{office_code}")
    public ResponseEntity<Office> findOfficeByOfficeCode(@PathVariable("office_code")String officecode)
    {
        Office office = officeservice.findByOfficeCode(officecode);
        System.out.println(office);
        return new ResponseEntity<Office>(office,HttpStatus.OK);
    }
	
	//Retrieve Offices which are in these two cities.
	@GetMapping("/{city1}/{city2}")
	public ResponseEntity<List<Employee>> findOfficesByTwoCities(@PathVariable("city1") String city1,@PathVariable("city2") String city2){
		List<Employee> emps=officeservice.getOfficesByTwoCityNames(city1, city2);
		return new ResponseEntity<List<Employee>>(emps,HttpStatus.OK);
	}
	
	//Retrieve Offices which are in these three cities.
	@GetMapping("/{city1}/{city2}/{city3}")
	public ResponseEntity<List<Employee>> findOfficesByThreeCities(@PathVariable("city1") String city1,@PathVariable("city2") String city2,@PathVariable("city3") String city3){
		List<Employee> emps=officeservice.getOfficesByThreeCityNames(city1, city2, city3);
		return new ResponseEntity<List<Employee>>(emps,HttpStatus.OK);
	}
	
	//Insert new Office.
	@PostMapping("/add")
	public ResponseEntity<SuccessResponse> addNewOffice(@RequestBody Office office){
		  Office addnewOffice = officeservice.addNewOffice(office);
		return new ResponseEntity<SuccessResponse>(new SuccessResponse(LocalDate.now(),"Office details added sucessfully"), HttpStatus.CREATED);
	}
	
	//Update Office Phone Number using officeCode.
	@PutMapping("/{office_code}/{phone_number}")
	public ResponseEntity<SuccessResponse> updatePhoneNumber(@PathVariable("office_code") String officeCode,@PathVariable("phone_number") String phoneNumber){
		Office office=officeservice.updatePhoneNumber(officeCode, phoneNumber);
		return new ResponseEntity<SuccessResponse>(new SuccessResponse(LocalDate.now(),"Office Phone Number updated sucessfully"), HttpStatus.OK);
	}

	//Update Office using officeCode.
	@PutMapping("/update/{office_code}")
	public ResponseEntity<SuccessResponse> updateOffice(@PathVariable("office_code") String officeCode, @RequestBody Office office){
		Office existingOffice=officeservice.updateOffice(officeCode);
        officeservice.addNewOffice(office);
            return new ResponseEntity<SuccessResponse>(new SuccessResponse(LocalDate.now(),"Office Details updated sucessfully"), HttpStatus.OK);
	}
	
	//Delete Office using officeCode.
	@DeleteMapping("/{office_code}")
	public ResponseEntity<SuccessResponse> deleteByOfficeCode(@PathVariable("office_code") String officecode){
		officeservice.deleteOffice(officecode);
		return new ResponseEntity<SuccessResponse>(new SuccessResponse(LocalDate.now(),"Deleted Successfully"),HttpStatus.ACCEPTED);
	}
	
	
}
