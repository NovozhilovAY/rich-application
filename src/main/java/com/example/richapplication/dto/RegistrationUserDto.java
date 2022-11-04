package com.example.richapplication.dto;

public class RegistrationUserDto {

    private String login;

    private String firstName;

    private String lastName;

    private String country;

    private String city;

    private String password;

    public RegistrationUserDto() {
    }

    public RegistrationUserDto(String login, String firstName, String lastName, String country, String city, String password) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.city = city;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
