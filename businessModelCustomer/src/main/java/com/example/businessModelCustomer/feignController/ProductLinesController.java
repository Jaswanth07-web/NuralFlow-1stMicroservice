package com.example.businessModelCustomer.feignController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.businessModelCustomer.VO.ProductLinesVO;
import com.example.businessModelCustomer.exception.SuccessResponse;
import com.example.businessModelCustomer.feignService.ProductLinesService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ProductLinesController {
	
	@Autowired
	ProductLinesService pClient;
	
	//Add new Product
    @PostMapping(value="/api/v1/productlines/add")
    public ResponseEntity<SuccessResponse> addProductLine(@RequestBody ProductLinesVO productline){
    	return pClient.addProductLine(productline);
    }
	
	@GetMapping("/api/v1/productlines/all")
	public List<ProductLinesVO> getAllProdLines(){
		return pClient.getAllProductLines();
	}
}
