package com.example.businessModelCustomer.SecurityConfig;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    // Constructor injection for JwtAuthenticationFilter
    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    // Define a SecurityFilterChain bean to configure HTTP security
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
                .requestMatchers(HttpMethod.POST, "/api/v1/auth/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/employee/add").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/v1/employee/all").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/v1/employee/{employee_no}").hasRole("ADMIN")
                .anyRequest().permitAll()
            .and()
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    
    @Bean

	public CorsConfigurationSource corsConfigurationSource() {

	    CorsConfiguration corsConfiguration = new CorsConfiguration();

	    //Make the below setting as * to allow connection from any hos

	    corsConfiguration.setAllowedOrigins(List.of("http://localhost:4200"));

	    corsConfiguration.setAllowedMethods(List.of("GET", "POST","PUT"));

	    corsConfiguration.setAllowCredentials(true);

	    corsConfiguration.setAllowedHeaders(List.of("*"));

	    corsConfiguration.setMaxAge(3600L);

	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

	    source.registerCorsConfiguration("/**", corsConfiguration);

	    return source;

	}


    // Password encoder bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // AuthenticationManager bean
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = 
            http.getSharedObject(AuthenticationManagerBuilder.class);
        // Configure your authentication provider here
        // For example, using in-memory authentication:
        authenticationManagerBuilder.inMemoryAuthentication()
            .withUser ("user").password(passwordEncoder().encode("password")).roles("NonAdmin");
        return authenticationManagerBuilder.build();
    }
}