package com.example.businessModelCustomer.VO;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.example.businessModelCustomer.entity.Customer;

public class OrdersVO {
    private final int orderNumber;
    private final Date orderDate;
    private final Date requiredDate;
    private final Date shippedDate;
    private final String status;
    private final String comments;
    private final Customer customers;  // Use CustomerVO instead of the entity Customer
    private final List<OrderDetailsVO> orderDetails;  // Use OrderDetailsVO instead of the entity OrderDetails

    // Constructor to initialize all fields
    public OrdersVO(int orderNumber, Date orderDate, Date requiredDate, Date shippedDate, 
                    String status, String comments, Customer customers, List<OrderDetailsVO> orderDetails) {
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.requiredDate = requiredDate;
        this.shippedDate = shippedDate;
        this.status = status;
        this.comments = comments;
        this.customers = customers;
        this.orderDetails = orderDetails;
    }

    // Getters (no setters, as the object is immutable)
    public int getOrderNumber() {
        return orderNumber;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Date getRequiredDate() {
        return requiredDate;
    }

    public Date getShippedDate() {
        return shippedDate;
    }

    public String getStatus() {
        return status;
    }

    public String getComments() {
        return comments;
    }

    public Customer getCustomers() {
        return customers;
    }

    public List<OrderDetailsVO> getOrderDetails() {
        return orderDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdersVO ordersVO = (OrdersVO) o;
        return orderNumber == ordersVO.orderNumber &&
                Objects.equals(orderDate, ordersVO.orderDate) &&
                Objects.equals(requiredDate, ordersVO.requiredDate) &&
                Objects.equals(shippedDate, ordersVO.shippedDate) &&
                Objects.equals(status, ordersVO.status) &&
                Objects.equals(comments, ordersVO.comments) &&
                Objects.equals(customers, ordersVO.customers) &&
                Objects.equals(orderDetails, ordersVO.orderDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderNumber, orderDate, requiredDate, shippedDate, status, comments, customers, orderDetails);
    }

    @Override
    public String toString() {
        return "OrdersVO{" +
                "orderNumber=" + orderNumber +
                ", orderDate=" + orderDate +
                ", requiredDate=" + requiredDate +
                ", shippedDate=" + shippedDate +
                ", status='" + status + '\'' +
                ", comments='" + comments + '\'' +
                ", customers=" + customers +
                ", orderDetails=" + orderDetails +
                '}';
    }
}

