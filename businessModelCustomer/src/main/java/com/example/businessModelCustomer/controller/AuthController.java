package com.example.businessModelCustomer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import com.example.businessModelCustomer.SecurityConfig.JwtUtil;
import com.example.businessModelCustomer.SecurityConfig.UserCredentialsService;
import com.example.businessModelCustomer.entity.LoginRequest;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private UserCredentialsService userCredentialsService;
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws Exception {
        // Authenticate the user using loginRequest credentials (username, password)
        // If authentication is successful, issue a JWT token
    	System.err.println("Login Was Called");
    	String role = userCredentialsService.getRoleByUsername(loginRequest.getUsername());
        System.err.println(role);
        if ("admin".equalsIgnoreCase(role)) {
        	System.err.println("If Was Called");
            String token = jwtUtil.generateToken(loginRequest.getUsername(), role);
            return ResponseEntity.ok(new AuthResponse(token));
        }else {
        	System.err.println("Else Was Called");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
