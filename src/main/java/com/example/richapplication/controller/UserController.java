package com.example.richapplication.controller;

import com.example.richapplication.dto.Payment;
import com.example.richapplication.model.User;
import com.example.richapplication.model.UserWithRating;
import com.example.richapplication.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/city/{city}")
    List<User> getUsersByCity(@PathVariable(name = "city")String city){
        return userService.getUsersByCity(city);
    }

    @GetMapping("/country/{country}")
    List<User> getUsersByCountry(@PathVariable(name = "country")String country){
        return userService.getUsersByCountry(country);
    }

    @GetMapping("/{id}")
    UserWithRating getUserById(@PathVariable(name = "id") Integer id){
        return userService.getUserWithRatingByID(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void deleteUserById(@PathVariable(name = "id") Integer id){
        userService.deleteUserByID(id);
    }

    @PostMapping
    User saveUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @PutMapping
    User updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @PostMapping("/payment")
    User makePayment(@RequestBody Payment payment){
        return userService.makePayment(payment);
    }

}
