package com.example.businessModelCustomer.feignService;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.businessModelCustomer.VO.ProductLinesVO;
import com.example.businessModelCustomer.exception.SuccessResponse;

@FeignClient(name="productLines-service",url="http://localhost:9090")
public interface ProductLinesService {

     @PostMapping(value="/api/v1/productlines/add")
     public ResponseEntity<SuccessResponse> addProductLine(@RequestBody ProductLinesVO productline);
	
	 @GetMapping("/api/v1/productlines/all")
	 public List<ProductLinesVO> getAllProductLines();
}
