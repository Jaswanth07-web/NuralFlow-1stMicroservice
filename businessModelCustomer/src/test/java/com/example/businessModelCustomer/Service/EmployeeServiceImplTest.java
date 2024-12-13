package com.example.businessModelCustomer.Service;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
 
import com.example.businessModelCustomer.entity.Employee;
import com.example.businessModelCustomer.entity.Office;
import com.example.businessModelCustomer.exception.EmployeeNotFoundException;
import com.example.businessModelCustomer.exception.OfficeNotFoundException;
import com.example.businessModelCustomer.repository.EmployeeRepository;
import com.example.businessModelCustomer.repository.OfficeRepsoitory;
import com.example.businessModelCustomer.service.EmployeeServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
class EmployeeServiceImplTest {
	@Mock
    private EmployeeRepository employeeRepository;
 
    @Mock
    private OfficeRepsoitory officeRepository;
 
    @InjectMocks
    private EmployeeServiceImpl employeeService;
 
    private Employee employee;
    private Office office;
 
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
 
        office = new Office();
        office.setOfficeCode("1");
        office.setCity("San Francisco");
        office.setCountry("USA");
        office.setPhone("+1 650 219 4782");
        office.setPostalCode("94080");
        office.setTerritory("NA");
 
        employee = new Employee();
        employee.setEmployeeNumber(1002);
        employee.setFirstName("Diane");
        employee.setLastName("Murphy");
        employee.setEmail("dmurphy@classicmodelcars.com");
        employee.setJobTitle("President");
        employee.setRole("Admin");
        employee.setOffice(office);
        employee.setReportsTo(null);
    }
 
    @Test
    public void testGetAllEmployees() {
        when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee));
 
        List<Employee> employees = employeeService.getAllEmployees();
        assertNotNull(employees);
        assertEquals(1, employees.size());
        assertEquals("Diane", employees.get(0).getFirstName());
    }
 
    @Test
    public void testGetEmployeeByNumber_Valid() {
        when(employeeRepository.findById(1002)).thenReturn(Optional.of(employee));
 
        Employee foundEmployee = employeeService.getEmployeeByNumber(1002);
        assertNotNull(foundEmployee);
        assertEquals("Diane", foundEmployee.getFirstName());
    }
 
    @Test
    public void testGetEmployeeByNumber_NotFound() {
        when(employeeRepository.findById(9999)).thenReturn(Optional.empty());
 
        EmployeeNotFoundException exception = assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.getEmployeeByNumber(9999);
        });
        assertEquals("Employee not found with ID: 9999", exception.getMessage());
    }
 
    @Test
    public void testFindByOfficeCode_Valid() {
        when(employeeRepository.findByOfficeCode("1")).thenReturn(Arrays.asList(employee));
 
        List<Employee> employees = employeeService.findByOfficeCode("1");
        assertNotNull(employees);
        assertEquals(1, employees.size());
        assertEquals("Diane", employees.get(0).getFirstName());
    }
 
    @Test
    public void testFindByOfficeCode_NotFound() {
        when(employeeRepository.findByOfficeCode("99")).thenReturn(Arrays.asList());
 
        OfficeNotFoundException exception = assertThrows(OfficeNotFoundException.class, () -> {
            employeeService.findByOfficeCode("99");
        });
        assertEquals("No employees found for office code: 99", exception.getMessage());
    }
 
    @Test
    public void testGetEmployeesByCity_Valid() {
        when(employeeRepository.findByCity("San Francisco")).thenReturn(Arrays.asList(employee));
 
        List<Employee> employees = employeeService.getEmployeesByCity("San Francisco");
        assertNotNull(employees);
        assertEquals(1, employees.size());
        assertEquals("Diane", employees.get(0).getFirstName());
    }
 
    @Test
    public void testGetEmployeesByCity_NotFound() {
        when(employeeRepository.findByCity("NonExistentCity")).thenReturn(Arrays.asList());
 
        EmployeeNotFoundException exception = assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.getEmployeesByCity("NonExistentCity");
        });
        assertEquals("No employees found in city: NonExistentCity", exception.getMessage());
    }
 
    @Test
    public void testAddEmployee_Valid() {
        when(employeeRepository.existsById(1003)).thenReturn(false);
        when(employeeRepository.save(employee)).thenReturn(employee);
 
        Employee savedEmployee = employeeService.addEmployee(employee);
        assertNotNull(savedEmployee);
        assertEquals("Diane", savedEmployee.getFirstName());
    }
 
    @Test
    public void testAddEmployee_AlreadyExists() {
        when(employeeRepository.existsById(1002)).thenReturn(true);
 
        EmployeeNotFoundException exception = assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.addEmployee(employee);
        });
        assertEquals("Employee with ID 1002 already exists.", exception.getMessage());
    }
 
    @Test
    public void testUpdateEmployee_Valid() {
        Employee updatedDetails = new Employee();
        updatedDetails.setFirstName("UpdatedDiane");
        updatedDetails.setLastName("UpdatedMurphy");
        updatedDetails.setEmail("updated_dm@classicmodelcars.com");
 
        when(employeeRepository.findById(1002)).thenReturn(Optional.of(employee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(updatedDetails);
 
        Employee updatedEmployee = employeeService.updateEmployee(1002, updatedDetails);
        assertNotNull(updatedEmployee);
        assertEquals("UpdatedDiane", updatedEmployee.getFirstName());
    }
 
    @Test
    public void testUpdateEmployeeRole_Valid() {
        when(employeeRepository.findById(1002)).thenReturn(Optional.of(employee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
 
        Employee updatedEmployee = employeeService.updateEmployeeRole(1002, "NewRole");
        assertNotNull(updatedEmployee);
        assertEquals("NewRole", updatedEmployee.getRole());
    }
 
    @Test
    public void testDeleteEmployee_Valid() {
        when(employeeRepository.findById(1002)).thenReturn(Optional.of(employee));
 
        employeeService.deleteEmployee(1002);
        verify(employeeRepository, times(1)).delete(employee);
    }
 
    @Test
    public void testDeleteEmployee_NotFound() {
        when(employeeRepository.findById(9999)).thenReturn(Optional.empty());
 
        EmployeeNotFoundException exception = assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.deleteEmployee(9999);
        });
        assertEquals("Employee not found with ID: 9999", exception.getMessage());
    }
 
}
 