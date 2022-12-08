package com.example.richapplication.api;

import com.example.richapplication.dto.Payment;
import com.example.richapplication.dto.UpdateUserDto;
import com.example.richapplication.exceptions.ResourceNotFoundException;
import com.example.richapplication.model.User;
import com.example.richapplication.model.UserWithRating;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    List<User> getUsersByCity(String city);

    List<User> getUsersByCountry(String country);

    User getUserByID(Integer id);

    User getUserByUsername(String userName);

    UserWithRating getUserWithRatingByID(Integer id);

    void deleteUserByID(Integer id);

    User addUser(User user);

    User updateUser(User user);

    User updateUser(UpdateUserDto updateUserDto);

    User makePayment(Payment payment);
}
