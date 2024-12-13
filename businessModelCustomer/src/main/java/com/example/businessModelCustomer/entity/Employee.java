package com.example.businessModelCustomer.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "employees")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "employeeNumber")
public class Employee {

    @Id
    private int employeeNumber;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 50, nullable = false)
    private String lastName;

    @Column(length = 50, nullable = false)
    private String firstName;

    @Column(length = 10, nullable = false)
    private String extension;

    @Column(length = 50, nullable = false)
    private String jobTitle;

//    @ManyToOne
//    @JoinColumn(name = "reportsTo")
//    @JsonBackReference("reportsToReference")  // Unique name for reportsTo relationship
//    private Employee reportsTo;
    
    @Column(length = 50, nullable = false)
    private String role;

//    @ManyToOne
//    @JoinColumn(name = "office_code", nullable = false)
//    @JsonBackReference("officeReference")  // Unique name for office relationship
//    private Office office;

    @OneToMany(mappedBy = "emp", cascade = CascadeType.ALL)
    @JsonIgnore  // Prevent serialization of customers
    private List<Customer> customers;

//    @OneToMany(mappedBy = "reportsTo", cascade = CascadeType.ALL)
//    @JsonIgnore  // Prevent serialization of reports field to avoid infinite recursion
//    @Column(insertable = false, updatable = false)  // Prevent updating the same column for the reports relationship
//    private List<Employee> reports;
    
    // Relationships
    @ManyToOne
    @JoinColumn(name = "office_code", nullable = false)
    private Office office;

    @ManyToOne
    @JoinColumn(name = "reports_to")
    private Employee reportsTo; // Self-referencing relationship

    @OneToMany(mappedBy = "reportsTo")
    private List<Employee> reports; // Employees that report to this one
    
    public Employee() {
        super();
    }

    public Employee(String email, String lastName, String firstName, String extension, String jobTitle,
                    Employee reportsTo, String role, Office office, List<Customer> customers,
                    List<Employee> reports) {
        super();
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
        this.extension = extension;
        this.jobTitle = jobTitle;
        this.reportsTo = reportsTo;
        this.role = role;
        this.office = office;
        this.customers = customers;
        this.reports = reports;
    }

    public Employee(int employeeNumber, String email, String lastName, String firstName, String extension,
                    String jobTitle, Employee reportsTo, String role, Office office, List<Customer> customers,
                    List<Employee> reports) {
        super();
        this.employeeNumber = employeeNumber;
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
        this.extension = extension;
        this.jobTitle = jobTitle;
        this.reportsTo = reportsTo;
        this.role = role;
        this.office = office;
        this.customers = customers;
        this.reports = reports;
    }

    // Getter and Setter methods

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Employee getReportsTo() {
        return reportsTo;
    }

    public void setReportsTo(Employee reportsTo) {
        this.reportsTo = reportsTo;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public List<Employee> getReports() {
        return reports;
    }

    public void setReports(List<Employee> reports) {
        this.reports = reports;
    }

    @Override
    public String toString() {
        return "Employee [employeeNumber=" + employeeNumber + ", email=" + email + ", lastName=" + lastName
                + ", firstName=" + firstName + ", extension=" + extension + ", jobTitle=" + jobTitle + ", reportsTo="
                + reportsTo + ", role=" + role + ", office=" + office + ", customers=" + customers + ", reports="
                + reports + "]";
    }
}
