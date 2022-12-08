package com.example.richapplication.controller;

import com.example.richapplication.api.RegistrationService;
import com.example.richapplication.dto.AuthResponse;
import com.example.richapplication.dto.RegistrationUserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/registration")
@CrossOrigin("*")
public class RegistrationController {
    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    public ResponseEntity<AuthResponse> registration(@RequestBody RegistrationUserDto userDto) {
        return registrationService.addNewUser(userDto);
    }
}
