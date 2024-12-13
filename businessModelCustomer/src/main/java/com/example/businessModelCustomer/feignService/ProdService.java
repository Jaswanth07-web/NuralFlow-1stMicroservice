package com.example.businessModelCustomer.feignService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.businessModelCustomer.VO.OrdersVO;
import com.example.businessModelCustomer.VO.ProductVO;
import com.example.businessModelCustomer.exception.SuccessResponse;


@FeignClient(name="product-service",url="http://localhost:9090")
public interface ProdService {
	
	 //products
	
     @PostMapping(value="/api/v1/product/add")
     public ResponseEntity<SuccessResponse> addProduct(@RequestBody ProductVO product);
	
	 @GetMapping("/api/v1/product/all")
	 public List<ProductVO> getAllProducts();
	
	 @GetMapping("/api/v1/product/{product_code}")
	 public ResponseEntity<ProductVO> getProductsByProductCode(@PathVariable String product_code);
	 
	 @GetMapping("/api/v1/product/name/{product_name}")
	 public ResponseEntity<ProductVO> getProductsByProductName(@PathVariable String product_name);
	 
	 @GetMapping("/api/v1/product/scale/{product_scale}")
	 public ResponseEntity<List<ProductVO>> getProductsByScale(@PathVariable String product_scale);
	 
	 @GetMapping("/api/v1/product/vendor/{product_vendor}")
	 public ResponseEntity<List<ProductVO>> getProductByProductVendor(@PathVariable String product_vendor);
	 
	 @PutMapping("/api/v1/product/{product_code}/{buy_price}")
	 public ResponseEntity<SuccessResponse> updateProductPrice(
	            @PathVariable("product_code") String productCode,
	            @PathVariable("buy_price") BigDecimal buyPrice);
	 
	  @PutMapping("/api/v1/product/update_quantity/{product_code}/{quantity_in_stock}")
	  public ResponseEntity<SuccessResponse> updateProductQuantity(
	            @PathVariable("product_code") String productCode,
	            @PathVariable("quantity_in_stock") short quantityInStock);
	 
	  @PutMapping("/api/v1/product/msrp/{product_code}/{msrp}")
	  public ResponseEntity<SuccessResponse> updateProductMsrp(
	            @PathVariable("product_code") String productCode,
	            @PathVariable("msrp") BigDecimal msrp);
	 
	    @PutMapping("/api/v1/product/vendor/{product_code}/{product_vendor}")
	    public ResponseEntity<SuccessResponse> updateProductVendor(
	            @PathVariable("product_code") String productCode,
	            @PathVariable("product_vendor") String productVendor);

	    @PutMapping("/api/v1/product/name/{product_code}/{product_name}")
	    public ResponseEntity<SuccessResponse> updateProductName(
	            @PathVariable("product_code") String productCode,
	            @PathVariable("product_name") String productName);
	 
	    @PutMapping("/api/v1/product/scale/{product_code}/{product_scale}")
	    public ResponseEntity<SuccessResponse> updateProductScale(
	            @PathVariable("product_code") String productCode,
	            @PathVariable("product_scale") String productScale);
	    
	    @DeleteMapping("/api/v1/product/delete/{product_code}")
	    public ResponseEntity<SuccessResponse> deleteProduct(@PathVariable String product_code);
	    
	    @GetMapping("/api/v1/product/productDescription/{product_description}")
	    public ResponseEntity<ProductVO> getProductionDescription(@PathVariable String product_description);
	    
	    @GetMapping("/total_ordered_qty/{product_code}")
		public Integer getTotalOrderedQuantity(@PathVariable String product_code);
	 //orders
	 
	//Saves a new order to the database
	@PostMapping("/api/v1/orders/add")
    public ResponseEntity<Object> addOrder(@RequestBody OrdersVO orders);
	 
	 @GetMapping("/api/v1/orders/all")
	 public List<OrdersVO> getAllOrders() ;
	 
	 @GetMapping("/api/v1/orders/{order_number}")
	 public ResponseEntity<OrdersVO> getOrder(@PathVariable("order_number") int order_number);
	 
	 @GetMapping("/api/v1/orders/order_date/{order_date}")
	 public ResponseEntity<List<OrdersVO>> getOrderByOrderDate(@PathVariable("order_date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date orderDate);
	 
	 @GetMapping("/api/v1/orders/required_date/{required_date}")
	 public ResponseEntity<Object> getOrderByRequiredDate(@PathVariable("required_date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date requiredDate);

	 @GetMapping("/api/v1/orders/shipped_date/{shipped_date}")
	 public ResponseEntity<Object> getOrderByShippedDate(@PathVariable("shipped_date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date shippedDate);

	 @GetMapping("/api/v1/orders/status/{status}")
	 public ResponseEntity<List<OrdersVO>> getOrderByStatus(@PathVariable("status") String status);
	 
	 @GetMapping("/api/v1/orders/customer/{customer_number}")
	 public ResponseEntity<List<OrdersVO>> getAllOrdersByCustomerNumber(@PathVariable("customer_number") int customer_number);

	 @GetMapping("/api/v1/orders/{customer_number}/{status}")
	 public ResponseEntity<List<OrdersVO>> getAllOrdersByCustomerNumberAndStatus(@PathVariable("customer_number") int customer_number,@PathVariable("status") String status);

	@GetMapping("/api/v1/orders/products_details/{order_number}")
	public ResponseEntity<Object> getProductsByOrderNumber(@PathVariable("order_number") int order_number);

    @GetMapping("/api/v1/orders/products_shipped_details")
    public ResponseEntity<Object> getProductsByOrderStatus();

    @PutMapping("/api/v1/orders/{order_number}/{shipped_date}")
    public ResponseEntity<Object> updateOrderShippedDate(@PathVariable("order_number") int order_number,@PathVariable("shipped_date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date shipped_date);
    
    @PutMapping("/api/v1/orders/updateStatus/{order_number}/{status}")
    public ResponseEntity<Object> updateOrderStatusDate(@PathVariable("order_number") int order_number,@PathVariable("status") String status);

    @DeleteMapping("/api/v1/orders/{order_number}")
    public ResponseEntity<Object> deleteOrderByOrderNumber(@PathVariable("order_number") int order_number);
}
