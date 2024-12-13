package com.example.businessModelCustomer.service;

import java.util.List; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.businessModelCustomer.entity.Employee;
import com.example.businessModelCustomer.entity.Office;
import com.example.businessModelCustomer.exception.CityNotFoundException;
import com.example.businessModelCustomer.exception.FieldNotFoundException;
import com.example.businessModelCustomer.exception.OfficeCodeNotFoundException;
import com.example.businessModelCustomer.exception.OfficeNotFoundException;
import com.example.businessModelCustomer.repository.OfficeRepsoitory;
 
 
//Implementation for OfficeService

@Service
public class OfficeServiceImpl implements OfficeService{
	
	@Autowired
	private OfficeRepsoitory officerepository;
 
	@Override
	public List<Office> getAllOffices() {
		return officerepository.findAll();
	}
	@Override
	public Office findByOfficeCode(String officecode) {
		Office office = officerepository.findByOfficeCode(officecode);
		if(office==null) {
			throw new OfficeNotFoundException("Office Not Found with officeCode : "+officecode);
		}
		return office;
	}
 
	@Override
	public List<Employee> getOfficesByTwoCityNames(String city1, String city2) {
		if(officerepository.findByCity(city1).isEmpty()) {
			throw new CityNotFoundException("City not found : "+city1);
		}
		if(officerepository.findByCity(city2).isEmpty()) {
			throw new CityNotFoundException("City not found : "+city2);
		}
		List<Employee> emps=officerepository.findByTwoCites(city1, city2);
		return emps;

	}
 
	@Override
	public List<Employee> getOfficesByThreeCityNames(String city1, String city2, String city3) {
		if(officerepository.findByCity(city1).isEmpty()) {
			throw new CityNotFoundException("City not found : "+city1);
		}
		if(officerepository.findByCity(city2).isEmpty()) {
			throw new CityNotFoundException("City not found : "+city2);
		}
		if(officerepository.findByCity(city3).isEmpty()) {
			throw new CityNotFoundException("City not found : "+city3);
		}
		List<Employee> emps=officerepository.findByThreeCities(city1, city2, city3);
		return emps;
	}
 
	@Override
	public Office addNewOffice(Office office){
		if(office.getOfficeCode()==null) {
			throw new FieldNotFoundException("OfficeCode is not given : Post fail");
		}
		if(office.getAddressLine1()==null) {
			throw new FieldNotFoundException("AddressLine1 is not given : Post fail");
		}
		if(office.getCity()==null) {
			throw new FieldNotFoundException("City is not given : Post fail");
		}
		if(office.getCountry()==null) {
			throw new FieldNotFoundException("Country is not given : Post fail");
		}
		if(office.getPhone()==null) {
			throw new FieldNotFoundException("PhoneNumber is not given : Post fail");
		}
		if(office.getPostalCode()==null) {
			throw new FieldNotFoundException("PostalCode is not given : Post fail");
		}
		if(office.getTerritory()==null) {
			throw new FieldNotFoundException("Territory is not given : Post fail");
		}
		Office save = officerepository.save(office);
		return save;
	}
 
	@Override
	public Office updatePhoneNumber(String officecode, String phoneNumber) {
		Office office=officerepository.findByOfficeCode(officecode);
		if(office==null) {
			throw new OfficeNotFoundException("Office Not Found with officeCode : "+officecode);
		}
		officerepository.findByOfficeCode(officecode).setPhone(phoneNumber);
		
		officerepository.save(office);
		return office;
	}
 
	@Override
	public Office updateOffice(String officecode) {
		Office office=officerepository.findByOfficeCode(officecode);
		if(office==null) {
			throw new OfficeNotFoundException("Office Not Found with officeCode : "+officecode);
		}
		return office;
	}
 
	@Override
	public void deleteOffice(String officecode) {
		Office office = officerepository.findByOfficeCode(officecode);
		if(office==null) {
			throw new OfficeCodeNotFoundException("OfficeCode not found : "+officecode);
		}
		officerepository.deleteById(officecode);
	}

 
}