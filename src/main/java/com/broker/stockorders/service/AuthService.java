package com.broker.stockorders.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

   /* private final JwtUtil jwtUtil;


    // Basit bir login işlemi
    public String login(String username, String password) {
        // Burada sadece basit bir kontrol yapıyoruz.
        // Gerçek uygulamalarda bu kontrol veri tabanında yapılmalı ve şifre güvenli şekilde kontrol edilmelidir
        if ("admin".equals(username) && "password".equals(password)) {
            // Kullanıcı adı ve şifre doğruysa token oluştur
            return jwtUtil.generateToken(username);
        } else {
            // Yanlış kullanıcı adı ya da şifre durumu
            throw new RuntimeException("Invalid credentials");
        }
    }*/
}
