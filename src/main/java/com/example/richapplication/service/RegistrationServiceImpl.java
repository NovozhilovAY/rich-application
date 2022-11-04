package com.example.richapplication.service;

import com.example.richapplication.api.AuthService;
import com.example.richapplication.api.RegistrationService;
import com.example.richapplication.api.UserFactory;
import com.example.richapplication.dto.AuthRequest;
import com.example.richapplication.dto.AuthResponse;
import com.example.richapplication.dto.RegistrationUserDto;
import com.example.richapplication.exceptions.UserAlreadyExistsException;
import com.example.richapplication.model.User;
import com.example.richapplication.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class RegistrationServiceImpl implements RegistrationService {

    private final UserFactory userFactory;

    private final UserRepository userRepository;

    private final AuthService authService;

    public RegistrationServiceImpl(UserFactory userFactory, UserRepository userRepository, AuthService authService) {
        this.userFactory = userFactory;
        this.userRepository = userRepository;
        this.authService = authService;
    }

    @Override
    public ResponseEntity<AuthResponse> addNewUser(RegistrationUserDto userDto) {
        isUserAlreadyExist(userDto.getLogin());
        User userToAdd = userFactory.createUser(userDto);
        userRepository.save(userToAdd);
        return authService.login(new AuthRequest(userDto.getLogin(), userDto.getPassword()));
    }

    private void isUserAlreadyExist(String login) {
        if(userRepository.findUserByLogin(login).isPresent()) {
            throw new UserAlreadyExistsException("User with login - "+login+" already exists!");
        }
    }
}
