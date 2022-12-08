package com.example.richapplication.service;

import com.example.richapplication.api.UserService;
import com.example.richapplication.dto.Payment;
import com.example.richapplication.dto.UpdateUserDto;
import com.example.richapplication.exceptions.ResourceNotFoundException;
import com.example.richapplication.model.User;
import com.example.richapplication.model.UserWithRating;
import com.example.richapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Value("${root.image.url}")
    private String rootImageUrl;

    @Value("${default.picture.name}")
    private String defaultProfilePictureName;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public List<User> getUsersByCity(String city){
        return repository.getUsersByCityOrderByMoneyDesc(city);
    }

    public List<User> getUsersByCountry(String country){
        return repository.getUsersByCountryOrderByMoneyDesc(country);
    }

    public User getUserByID(Integer id){
        return repository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("User with id = "+ id +" not found"));
    }

    public UserWithRating getUserWithRatingByID(Integer id){
        User user = repository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("User with id = "+ id +" not found"));
        return getUserWithRating(user);
    }

    public void deleteUserByID(Integer id){
        User userToDelete = this.getUserByID(id);
        repository.delete(userToDelete);
    }

    @Override
    public User getUserByUsername(String userName) {
        return repository.findUserByLogin(userName).orElseThrow(
                ()->new ResourceNotFoundException("User with login = "+ userName +" not found"));
    }

    public User addUser(User user){
        setDefaultProfilePicture(user);
        return repository.save(user);
    }

    public User updateUser(User user){
        User userToUpdate = this.getUserByID(user.getId());
        return repository.save(user);
    }

    @Override
    public User updateUser(UpdateUserDto updateUserDto) {
        User userToUpdate = this.getUserByID(updateUserDto.getId());
        updateUserDto.mapFieldsToUser(userToUpdate);
        return repository.save(userToUpdate);
    }

    public User makePayment(Payment payment){
        repository.makePayment(payment.getId(), payment.getAmount());
        User updatedUser = this.getUserByID(payment.getId());
        return getUserByID(payment.getId());
    }

    private UserWithRating getUserWithRating(User user){
        UserWithRating userWithRating = new UserWithRating(user);
        userWithRating.setGlobalRating(getGlobalRating(user));
        userWithRating.setCountryRating(getCountryRating(user));
        userWithRating.setCityRating(getCityRating(user));
        return userWithRating;
    }

    private Integer getGlobalRating(User user){
        List<User> userList = repository.findAll();
        return userList.indexOf(user) + 1;
    }

    private Integer getCountryRating(User user){
        List<User> userList = repository.getUsersByCountryOrderByMoneyDesc(user.getCountry());
        return userList.indexOf(user) + 1;
    }

    private Integer getCityRating(User user){
        List<User> userList = repository.getUsersByCityOrderByMoneyDesc(user.getCity());
        return userList.indexOf(user) + 1;
    }

    private void setDefaultProfilePicture(User user){
        user.setProfilePicture(rootImageUrl + defaultProfilePictureName);
    }

}
