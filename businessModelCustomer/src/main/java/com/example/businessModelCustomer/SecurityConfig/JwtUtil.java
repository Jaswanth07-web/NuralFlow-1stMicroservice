package com.example.businessModelCustomer.SecurityConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

   // private String secretKey = "your_secret_key";  // Replace with a more secure key
	private final String SECRET_KEY = "your_secret_key_expected_to_be_a_big_key_else_error_occurs";
    // Generate a JWT token
    public String generateToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours expiration
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // Extract role from JWT token
    public String extractRole(String token) {
        Claims claims = extractClaims(token);
        return claims.get("role", String.class);
    }

    // Extract Claims from JWT token
    private Claims extractClaims(String token) {
    	try {
    		System.out.println(token);
            return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        } catch (MalformedJwtException e) {
            // Handle the exception
            System.out.println("Invalid JWT: " + e.getMessage());
            return null;
        } catch (Exception e) {
            // Handle any other exceptions
            System.out.println("Error parsing JWT: " + e.getMessage());
            return null;
        }
    }

    // Validate token
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaims(token).getExpiration();
    }

    // Validate the token
    public boolean validateToken(String token, String username) {
        return (username.equals(extractUsername(token)) && !isTokenExpired(token));
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }
}
