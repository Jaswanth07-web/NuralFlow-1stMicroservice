package com.example.businessModelCustomer.service;

import java.util.List;

import com.example.businessModelCustomer.entity.Employee;
import com.example.businessModelCustomer.entity.Office;

//Services for Offices

public interface OfficeService {
	List<Office> getAllOffices();
	Office findByOfficeCode(String officecode);
	List<Employee> getOfficesByTwoCityNames(String city1,String city2);
	List<Employee> getOfficesByThreeCityNames(String city1,String city2,String city3);
    Office addNewOffice(Office office);
    Office updatePhoneNumber(String officecode,String phoneNumber);
    Office updateOffice(String officecode);
    void deleteOffice(String officecode);
}