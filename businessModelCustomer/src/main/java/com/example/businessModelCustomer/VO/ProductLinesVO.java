package com.example.businessModelCustomer.VO;

import java.util.Arrays;
import java.util.List;

public class ProductLinesVO {
    private String productLine;
    private String textDescription;
    private String htmlDescription;
    private byte[] image;
    private List<ProductVO> product;

    // Default constructor
    public ProductLinesVO() {
        super();
    }

    // Constructor with fields
    public ProductLinesVO(String productLine, String textDescription, String htmlDescription, byte[] image,
                          List<ProductVO> product) {
        super();
        this.productLine = productLine;
        this.textDescription = textDescription;
        this.htmlDescription = htmlDescription;
        this.image = image;
        this.product = product;
    }

    // Getters and Setters
    public String getProductLine() {
        return productLine;
    }

    public void setProductLine(String productLine) {
        this.productLine = productLine;
    }

    public String getTextDescription() {
        return textDescription;
    }

    public void setTextDescription(String textDescription) {
        this.textDescription = textDescription;
    }

    public String getHtmlDescription() {
        return htmlDescription;
    }

    public void setHtmlDescription(String htmlDescription) {
        this.htmlDescription = htmlDescription;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public List<ProductVO> getProduct() {
        return product;
    }

    public void setProduct(List<ProductVO> product) {
        this.product = product;
    }

    // Override toString method for debugging
    @Override
    public String toString() {
        return "ProductLinesVO [productLine=" + productLine + ", textDescription=" + textDescription
                + ", htmlDescription=" + htmlDescription + ", image=" + Arrays.toString(image) + ", product=" + product
                + "]";
    }

    // Static factory method to construct ProductLinesVO from String (optional, if needed for specific use cases)
    public static ProductLinesVO fromString(String productLine) {
        ProductLinesVO pl = new ProductLinesVO();
        pl.setProductLine(productLine);
        return pl;
    }
}
