package com.example.businessModelCustomer.VO;

import java.math.BigDecimal;
import java.util.List;

public class ProductVO {
    private String productCode;
    private String productName;
    private ProductLinesVO productLine;
    private String productScale;
    private String productVendor;
    private String productDescription;
    private short quantityInStock;
    private BigDecimal buyPrice;
    private BigDecimal msrp;
    private List<OrderDetailsVO> orderDetails;

    public ProductVO() {
        // Default constructor
    }

    public ProductVO(String productName, ProductLinesVO productLine, String productScale, String productVendor,
                     String productDescription, short quantityInStock, BigDecimal buyPrice, BigDecimal msrp,
                     List<OrderDetailsVO> orderDetails) {
        this.productName = productName;
        this.productLine = productLine;
        this.productScale = productScale;
        this.productVendor = productVendor;
        this.productDescription = productDescription;
        this.quantityInStock = quantityInStock;
        this.buyPrice = buyPrice;
        this.msrp = msrp;
        this.orderDetails = orderDetails;
    }

    public ProductVO(String productCode, String productName, ProductLinesVO productLine, String productScale,
                     String productVendor, String productDescription, short quantityInStock, BigDecimal buyPrice,
                     BigDecimal msrp, List<OrderDetailsVO> orderDetails) {
        this.productCode = productCode;
        this.productName = productName;
        this.productLine = productLine;
        this.productScale = productScale;
        this.productVendor = productVendor;
        this.productDescription = productDescription;
        this.quantityInStock = quantityInStock;
        this.buyPrice = buyPrice;
        this.msrp = msrp;
        this.orderDetails = orderDetails;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public ProductLinesVO getProductLine() {
        return productLine;
    }

    public void setProductLine(ProductLinesVO productLine) {
        this.productLine = productLine;
    }

    public String getProductScale() {
        return productScale;
    }

    public void setProductScale(String productScale) {
        this.productScale = productScale;
    }

    public String getProductVendor() {
        return productVendor;
    }

    public void setProductVendor(String productVendor) {
        this.productVendor = productVendor;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public short getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(short quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public BigDecimal getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(BigDecimal buyPrice) {
        this.buyPrice = buyPrice;
    }

    public BigDecimal getMsrp() {
        return msrp;
    }

    public void setMsrp(BigDecimal msrp) {
        this.msrp = msrp;
    }

    public List<OrderDetailsVO> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailsVO> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public String toString() {
        return "ProductVO [productCode=" + productCode + ", productName=" + productName + ", productLine=" + productLine
                + ", productScale=" + productScale + ", productVendor=" + productVendor + ", productDescription=" 
                + productDescription + ", quantityInStock=" + quantityInStock + ", buyPrice=" + buyPrice + ", msrp="
                + msrp + ", orderDetails=" + orderDetails + "]";
    }
}
