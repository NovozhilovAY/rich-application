package com.example.richapplication.repository;

import com.example.richapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> getUsersByCity(String city);
    List<User> getUsersByCountry(String country);
}
