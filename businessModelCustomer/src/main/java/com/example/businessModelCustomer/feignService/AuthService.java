package com.example.businessModelCustomer.feignService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.businessModelCustomer.VO.LoginRequestVO;

@FeignClient(name="authentication-service",url="http://localhost:7777")
public interface AuthService {

	
	@PostMapping("/api/v1/auth/login")
	public ResponseEntity<?> login(@RequestBody LoginRequestVO loginRequest);
}
