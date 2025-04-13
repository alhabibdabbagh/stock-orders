package com.broker.stockorders.service;

import com.broker.stockorders.dto.request.LoginRequest;
import com.broker.stockorders.dto.response.LoginResponse;
import com.broker.stockorders.entity.Customer;
import com.broker.stockorders.repository.CustomerRepository;
import com.broker.stockorders.security.JwtTokenProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public LoginResponse authenticate(LoginRequest request) {
        // 1. Kullanıcıyı veritabanından bul
        Customer customer = customerRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        // 2. Şifre kontrolü
/*        if (!passwordEncoder.matches(request.getPassword(), customer.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }*/ //todo

        if (!request.getPassword().equals(customer.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        // 3. JWT token oluştur
        String token = jwtTokenProvider.generateToken(customer.getUsername(), customer.getRole());

        // 4. Response'u hazırla
        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setUsername(customer.getUsername());
        response.setRole(customer.getRole());
        response.setCustomerId(customer.getId());

        return response;
    }
}