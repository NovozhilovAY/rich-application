package com.example.richapplication.api;

import com.example.richapplication.dto.AuthResponse;
import com.example.richapplication.dto.RegistrationUserDto;
import org.springframework.http.ResponseEntity;

public interface RegistrationService {
    ResponseEntity<AuthResponse> addNewUser(RegistrationUserDto userDto);
}
