package com.example.richapplication.model;

public class UserWithRating extends User{
    private Integer globalRating;
    private Integer countryRating;
    private Integer cityRating;
    public UserWithRating(User user){
        setId(user.getId());
        setFirstName(user.getFirstName());
        setLastName(user.getLastName());
        setLogin(user.getLogin());
        setProfileDescription(user.getProfileDescription());
        setProfilePicture(user.getProfilePicture());
        setCountry(user.getCountry());
        setCity(user.getCity());
        setMoney(user.getMoney());
        setStatus(user.getStatus());
    }

    public UserWithRating() {
    }

    public UserWithRating(Integer id, String login, String firstName, String lastName, String profileDescription, String profilePicture, String country, String city, Double money, Integer globalRating, Integer countryRating, Integer cityRating, String status) {
        super(id, login, firstName, lastName, profileDescription,status, profilePicture, country, city, money);
        this.globalRating = globalRating;
        this.countryRating = countryRating;
        this.cityRating = cityRating;
    }

    public Integer getGlobalRating() {
        return globalRating;
    }

    public void setGlobalRating(Integer globalRating) {
        this.globalRating = globalRating;
    }

    public Integer getCountryRating() {
        return countryRating;
    }

    public void setCountryRating(Integer countryRating) {
        this.countryRating = countryRating;
    }

    public Integer getCityRating() {
        return cityRating;
    }

    public void setCityRating(Integer cityRating) {
        this.cityRating = cityRating;
    }
}
