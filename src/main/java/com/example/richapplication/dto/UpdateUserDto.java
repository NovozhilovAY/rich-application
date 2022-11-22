package com.example.richapplication.dto;

import com.example.richapplication.model.User;

public class UpdateUserDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String profileDescription;
    private String status;
    private String country;
    private String city;

    public UpdateUserDto() {
    }

    public UpdateUserDto(Integer id, String firstName, String lastName, String profileDescription, String status, String profilePicture, String country, String city) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profileDescription = profileDescription;
        this.status = status;
        this.country = country;
        this.city = city;
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

    public String getProfileDescription() {
        return profileDescription;
    }

    public void setProfileDescription(String profileDescription) {
        this.profileDescription = profileDescription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void mapFieldsToUser(User user) {
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setProfileDescription(profileDescription);
        user.setStatus(status);
        user.setCountry(country);
        user.setCity(city);
    }
}
