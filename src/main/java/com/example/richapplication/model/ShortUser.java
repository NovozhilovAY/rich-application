package com.example.richapplication.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class ShortUser {

    private Integer id;

    private Double money;

    private String firstName;

    private String lastName;

    private String profilePicture;

    private String status;

    public ShortUser() {
    }

    public ShortUser(Integer id, Double money, String firstName, String lastName, String profilePicture, String status) {
        this.id = id;
        this.money = money;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profilePicture = profilePicture;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static ShortUser fromUser(User user){
        return new ShortUser(user.getId(), user.getMoney(), user.getFirstName(), user.getLastName(), user.getProfilePicture(), user.getStatus());
    }

    public static List<ShortUser> fromUsers(List<User> users){
        List<ShortUser> shortUsers = new ArrayList<>();
        for (User user : users) {
            shortUsers.add(fromUser(user));
        }
        return shortUsers;
    }
}
