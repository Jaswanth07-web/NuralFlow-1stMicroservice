package com.example.businessModelCustomer.entity;

import java.io.Serializable;
import java.util.Objects;

public class PaymentId implements Serializable {
    private Integer customerNumber;
    private String checkNumber;

    // Default constructor
    public PaymentId() {}

    public PaymentId(Integer customerNumber, String checkNumber) {
        this.customerNumber = customerNumber;
        this.checkNumber = checkNumber;
    }

    // Getters and Setters
    public Integer getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(Integer customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }

    // Equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentId that = (PaymentId) o;
        return Objects.equals(customerNumber, that.customerNumber) &&
               Objects.equals(checkNumber, that.checkNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerNumber, checkNumber);
    }
}
