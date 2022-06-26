package com.example.richapplication.model;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@DynamicUpdate
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "login")
    private String login;


    @Column(name = "first_name", nullable = false)
    private String firstName;


    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "profile_descr", nullable = false)
    private String profileDescription;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "status", nullable = false)
    private String status;
    @Column(name = "profile_picture")
    private String profilePicture;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "money")
    private Double money;

    public User(Integer id, String login, String firstName, String lastName, String profileDescription, String status, String profilePicture, String country, String city, Double money) {
        this.id = id;
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profileDescription = profileDescription;
        this.status = status;
        this.profilePicture = profilePicture;
        this.country = country;
        this.city = city;
        this.money = money;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getProfileDescription() {
        return profileDescription;
    }

    public void setProfileDescription(String profileDescription) {
        this.profileDescription = profileDescription;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
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

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}