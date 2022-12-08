package com.example.richapplication.api;

import com.example.richapplication.dto.AuthRequest;
import com.example.richapplication.dto.AuthResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<AuthResponse> login(AuthRequest authRequest);
}
