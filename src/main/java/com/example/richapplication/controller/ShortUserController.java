package com.example.richapplication.controller;

import com.example.richapplication.api.UserService;
import com.example.richapplication.model.ShortUser;
import com.example.richapplication.service.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/short-users")
public class ShortUserController {
    private final UserService userService;

    public ShortUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    List<ShortUser> getAllUsers(){
        return ShortUser.fromUsers(userService.getAllUsers());
    }

    @GetMapping("/city/{city}")
    List<ShortUser> getUsersByCity(@PathVariable(name = "city")String city){
        return ShortUser.fromUsers(userService.getUsersByCity(city));
    }

    @GetMapping("/country/{country}")
    List<ShortUser> getUsersByCountry(@PathVariable(name = "country")String country){
        return ShortUser.fromUsers(userService.getUsersByCountry(country));
    }

    @GetMapping("/{id}")
    ShortUser getUserById(@PathVariable(name = "id") Integer id){
        return ShortUser.fromUser(userService.getUserByID(id));
    }
}
