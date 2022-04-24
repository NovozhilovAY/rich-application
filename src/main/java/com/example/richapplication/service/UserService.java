package com.example.richapplication.service;

import com.example.richapplication.dto.Payment;
import com.example.richapplication.exceptions.ResourceNotFoundException;
import com.example.richapplication.model.User;
import com.example.richapplication.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public List<User> getUsersByCity(String city){
        return repository.getUsersByCity(city);
    }

    public List<User> getUsersByCountry(String country){
        return repository.getUsersByCountry(country);
    }

    public User getUserByID(Integer id){
        return repository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("User with id = "+ id +" not found"));
    }

    public void deleteUserByID(Integer id){
        User userToDelete = this.getUserByID(id);
        repository.delete(userToDelete);
    }

    public User addUser(User user){
        return repository.save(user);
    }

    public User updateUser(User user){
        User userToUpdate = this.getUserByID(user.getId());
        repository.delete(userToUpdate);
        return repository.save(user);
    }

    public User makePayment(Payment payment){
        User userToUpdate = this.getUserByID(payment.getId());
        userToUpdate.setMoney(userToUpdate.getMoney() + payment.getAmount());
        return repository.save(userToUpdate);
    }

}
