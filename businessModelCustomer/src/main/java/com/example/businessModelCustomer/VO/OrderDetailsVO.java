package com.example.businessModelCustomer.VO;

import java.math.BigDecimal;
import java.util.Objects;

public class OrderDetailsVO {

    private final int orderNumber;
    private final String productCode;
    private final int quantityOrdered;
    private final BigDecimal priceEach;
    private final short orderLineNumber;

    // Constructor to initialize all fields
    public OrderDetailsVO(int orderNumber, String productCode, int quantityOrdered, BigDecimal priceEach, short orderLineNumber) {
        this.orderNumber = orderNumber;
        this.productCode = productCode;
        this.quantityOrdered = quantityOrdered;
        this.priceEach = priceEach;
        this.orderLineNumber = orderLineNumber;
    }

    // Getters (no setters as it's immutable)
    public int getOrderNumber() {
        return orderNumber;
    }

    public String getProductCode() {
        return productCode;
    }

    public int getQuantityOrdered() {
        return quantityOrdered;
    }

    public BigDecimal getPriceEach() {
        return priceEach;
    }

    public short getOrderLineNumber() {
        return orderLineNumber;
    }

    // Override equals and hashCode based on the business logic
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetailsVO that = (OrderDetailsVO) o;
        return orderNumber == that.orderNumber &&
                orderLineNumber == that.orderLineNumber &&
                quantityOrdered == that.quantityOrdered &&
                Objects.equals(productCode, that.productCode) &&
                Objects.equals(priceEach, that.priceEach);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderNumber, productCode, quantityOrdered, priceEach, orderLineNumber);
    }

    @Override
    public String toString() {
        return "OrderDetailsVO{" +
                "orderNumber=" + orderNumber +
                ", productCode='" + productCode + '\'' +
                ", quantityOrdered=" + quantityOrdered +
                ", priceEach=" + priceEach +
                ", orderLineNumber=" + orderLineNumber +
                '}';
    }
}
