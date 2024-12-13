package com.example.businessModelCustomer.feignController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.businessModelCustomer.VO.OrdersVO;
import com.example.businessModelCustomer.VO.ProductVO;
import com.example.businessModelCustomer.exception.SuccessResponse;
import com.example.businessModelCustomer.feignService.ProdService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ProdController {

	@Autowired
	ProdService pClient;
	
	
	//Add new Product
    @PostMapping(value="/api/v1/product/add")
    public ResponseEntity<SuccessResponse> addProduct(@RequestBody ProductVO product){
    	return pClient.addProduct(product);
    }
	
	@GetMapping("/api/v1/product/all")
	public List<ProductVO> getAllPlanes(){
		return pClient.getAllProducts();
	}
	
	@GetMapping("/api/v1/product/{product_code}")
	public ResponseEntity<ProductVO> getProductsByProductCode(@PathVariable String product_code){
		return pClient.getProductsByProductCode(product_code);
	}
	
	//Get Product by product_name
    @GetMapping("/api/v1/product/name/{product_name}")
    public ResponseEntity<ProductVO> getProductsByProductName(@PathVariable String product_name){
    	return pClient.getProductsByProductName(product_name);
    }
    
    //Get Product by product_scale
    @GetMapping("/api/v1/product/scale/{product_scale}")
    public ResponseEntity<List<ProductVO>> getProductsByScale(@PathVariable String product_scale){
    	return pClient.getProductsByScale(product_scale);
    }
	
    //Get Product by product_vendor
    @GetMapping("/api/v1/product/vendor/{product_vendor}")
    public ResponseEntity<List<ProductVO>> getProductByProductVendor(@PathVariable String product_vendor){
    	return pClient.getProductByProductVendor(product_vendor);
    }
	
    //Update Product’s Buy Price
    @PutMapping("/api/v1/product/{product_code}/{buy_price}")
    public ResponseEntity<SuccessResponse> updateProductPrice(
            @PathVariable("product_code") String productCode,
            @PathVariable("buy_price") BigDecimal buyPrice) {
    	return pClient.updateProductPrice(productCode, buyPrice);
    }
    
    //Update Product’s quantity_in_stock
    @PutMapping("/api/v1/product/update_quantity/{product_code}/{quantity_in_stock}")
    public ResponseEntity<SuccessResponse> updateProductQuantity(
            @PathVariable("product_code") String productCode,
            @PathVariable("quantity_in_stock") short quantityInStock){
    	return pClient.updateProductQuantity(productCode, quantityInStock);
    }
    
    //Update Product’s MSRP
    @PutMapping("/api/v1/product/msrp/{product_code}/{msrp}")
    public ResponseEntity<SuccessResponse> updateProductMsrp(
            @PathVariable("product_code") String productCode,
            @PathVariable("msrp") BigDecimal msrp){
    	return pClient.updateProductMsrp(productCode, msrp);
    }
    
    //Update Product’s vendor
    @PutMapping("/api/v1/product/vendor/{product_code}/{product_vendor}")
    public ResponseEntity<SuccessResponse> updateProductVendor(
            @PathVariable("product_code") String productCode,
            @PathVariable("product_vendor") String productVendor){
    	return pClient.updateProductVendor(productCode, productVendor);
    }
    
    //Update Product’s Product name
    @PutMapping("/api/v1/product/name/{product_code}/{product_name}")
    public ResponseEntity<SuccessResponse> updateProductName(
            @PathVariable("product_code") String productCode,
            @PathVariable("product_name") String productName) {
    	return pClient.updateProductName(productCode, productName);
    }
    
    //Update Product’s Product Scale
    @PutMapping("/api/v1/product/scale/{product_code}/{product_scale}")
    public ResponseEntity<SuccessResponse> updateProductScale(
            @PathVariable("product_code") String productCode,
            @PathVariable("product_scale") String productScale){
    		return pClient.updateProductScale(productCode, productScale);
    }
    
    //Delete Product By ProductCode
    @DeleteMapping("/api/v1/product/delete/{product_code}")
    public ResponseEntity<SuccessResponse> deleteProduct(@PathVariable String product_code){
    	return pClient.deleteProduct(product_code);
    }
    
    //Get Product By Product Description
    @GetMapping("/api/v1/product/productDescription/{product_description}")
    public ResponseEntity<ProductVO> getProductionDescription(@PathVariable String product_description){
    	return pClient.getProductionDescription(product_description);
    }
    
    //Get Total Ordered Quantity
    @GetMapping("/total_ordered_qty/{product_code}")
	public Integer getTotalOrderedQuantity(@PathVariable String product_code) {
    	return pClient.getTotalOrderedQuantity(product_code);
    }
    
	//orders
	
	//Saves a new order to the database
	@PostMapping("/api/v1/orders/add")
	public ResponseEntity<Object> addOrder(@RequestBody OrdersVO orders) {
		return pClient.addOrder(orders);
	}
	
	//Retrieves all orders from the database.
	@GetMapping("/api/v1/orders/all")
	 public List<OrdersVO> getAllOrders(){
		return pClient.getAllOrders();
	}
	
	//Retrieves an order by its order number.
	@GetMapping("/api/v1/orders/{order_number}")
    public ResponseEntity<OrdersVO> getOrder(@PathVariable("order_number") int order_number){
		return pClient.getOrder(order_number);
	}
	
	//Retrieves all orders with the specified order date.
	@GetMapping("/api/v1/orders/order_date/{order_date}")
	 public ResponseEntity<List<OrdersVO>> getOrderByOrderDate(@PathVariable("order_date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date orderDate){
		return pClient.getOrderByOrderDate(orderDate);
	}
	
	//Retrieves all orders with the specified required date.
	@GetMapping("/api/v1/orders/required_date/{required_date}")
	public ResponseEntity<Object> getOrderByRequiredDate(@PathVariable("required_date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date requiredDate){
	    return pClient.getOrderByRequiredDate(requiredDate);
	}
	
	@GetMapping("/api/v1/orders/shipped_date/{shipped_date}")
	 public ResponseEntity<Object> getOrderByShippedDate(@PathVariable("shipped_date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date shippedDate){
		return pClient.getOrderByShippedDate(shippedDate);
	}
	
	//Retrieves all orders with the specified status.
    @GetMapping("/api/v1/orders/status/{status}")
    public ResponseEntity<List<OrdersVO>> getOrderByStatus(@PathVariable("status") String status){
    	return pClient.getOrderByStatus(status);
    }

    //Retrieves all orders for a specific customer.
    @GetMapping("/api/v1/orders/customer/{customer_number}")
    public ResponseEntity<List<OrdersVO>> getAllOrdersByCustomerNumber(@PathVariable("customer_number") int customer_number){
    	return pClient.getAllOrdersByCustomerNumber(customer_number);
    }
    
    // Retrieves all orders for a specific customer with the specified status.
    @GetMapping("/api/v1/orders/{customer_number}/{status}")
    public ResponseEntity<List<OrdersVO>> getAllOrdersByCustomerNumberAndStatus(@PathVariable("customer_number") int customer_number,@PathVariable("status") String status){
    	return pClient.getAllOrdersByCustomerNumberAndStatus(customer_number, status);
    }
    
    //Retrieves all products associated with a specific order number.
    @GetMapping("/api/v1/orders/products_details/{order_number}")
    public ResponseEntity<Object> getProductsByOrderNumber(@PathVariable("order_number") int order_number){
    	return pClient.getProductsByOrderNumber(order_number);
    }
    
    //Retrieves all products from shipped orders.
    @GetMapping("/api/v1/orders/products_shipped_details")
    public ResponseEntity<Object> getProductsByOrderStatus(){
    	return pClient.getProductsByOrderStatus();
    }
    
    //Updates the shipped date for a specific order.
    @PutMapping("/api/v1/orders/{order_number}/{shipped_date}")
    public ResponseEntity<Object> updateOrderShippedDate(@PathVariable("order_number") int order_number,@PathVariable("shipped_date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date shipped_date){
    	return pClient.updateOrderShippedDate(order_number, shipped_date);
    }
    
    //Updates the status of a specific order.
    @PutMapping("/api/v1/orders/updateStatus/{order_number}/{status}")
    public ResponseEntity<Object> updateOrderStatusDate(@PathVariable("order_number") int order_number,@PathVariable("status") String status){
    	return  pClient.updateOrderStatusDate(order_number, status);
    }
    
    //Deletes a specific order by its order number. 
    @DeleteMapping("/api/v1/orders/{order_number}")
    public ResponseEntity<Object> deleteOrderByOrderNumber(@PathVariable("order_number") int order_number){
    	return pClient.deleteOrderByOrderNumber(order_number);
    }
    
    
    
}
