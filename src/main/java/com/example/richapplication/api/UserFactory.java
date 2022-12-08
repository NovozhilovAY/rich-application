package com.example.richapplication.api;

import com.example.richapplication.dto.RegistrationUserDto;
import com.example.richapplication.model.User;

public interface UserFactory {
    User createUser(RegistrationUserDto userDto);
}
