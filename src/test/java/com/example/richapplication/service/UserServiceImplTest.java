package com.example.richapplication.service;

import com.example.richapplication.api.UserService;
import com.example.richapplication.exceptions.ResourceNotFoundException;
import com.example.richapplication.model.Role;
import com.example.richapplication.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void deleteUserByIdWorks() {
        List<Role> roleList = new ArrayList<>();
        roleList.add(new Role(2, "ROLE_USER"));
        User user = new User(100, "test", "test", "test", "test", "test", "test", "test", "test", 0.0, "test", roleList);
        User addedUser = userService.addUser(user);

        Assertions.assertDoesNotThrow(()->userService.deleteUserByID(addedUser.getId()));
    }

    @Test
    public void deleteUserByIdThrowsException() {
        Integer wrongId = 999999999;

        Assertions.assertThrowsExactly(ResourceNotFoundException.class, ()->userService.deleteUserByID(wrongId));
    }


    @Test
    public void getUserByUserNameWorks() {
        List<Role> roleList = new ArrayList<>();
        roleList.add(new Role(2, "ROLE_USER"));
        User user = new User(100, "test", "test", "test", "test", "test", "test", "test", "test", 0.0, "test", roleList);
        User addedUser = userService.addUser(user);

        Assertions.assertDoesNotThrow(()->userService.getUserByUsername("test"));

        userService.deleteUserByID(addedUser.getId());
    }

    @Test
    public void getUserByUserNameThrowsException() {
        String wrongName = "test_______";
        Assertions.assertThrowsExactly(ResourceNotFoundException.class, ()->userService.getUserByUsername(wrongName));
    }

    @Test
    public void getUserByUserByIdWorks() {
        List<Role> roleList = new ArrayList<>();
        roleList.add(new Role(2, "ROLE_USER"));
        User user = new User(100, "test", "test", "test", "test", "test", "test", "test", "test", 0.0, "test", roleList);
        User addedUser = userService.addUser(user);

        Assertions.assertDoesNotThrow(()-> userService.getUserByID(addedUser.getId()));

        userService.deleteUserByID(addedUser.getId());
    }

    @Test
    public void getUserByUserByIdThrowsException() {
        Integer wrongId = 999999999;
        Assertions.assertThrowsExactly(ResourceNotFoundException.class, ()-> userService.deleteUserByID(wrongId));
    }



}