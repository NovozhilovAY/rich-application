package com.example.richapplication.factory;

import com.example.richapplication.api.UserFactory;
import com.example.richapplication.dto.RegistrationUserDto;
import com.example.richapplication.model.Role;
import com.example.richapplication.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserFactoryImpl implements UserFactory {

    @Value("${root.image.url}")
    private String rootImageUrl;

    @Value("${default.picture.name}")
    private String defaultPictureName;

    private final PasswordEncoder passwordEncoder;

    public UserFactoryImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(RegistrationUserDto userDto) {
        User user = new User();
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        user.setLastName(userDto.getLastName());
        user.setFirstName(userDto.getFirstName());
        user.setCity(userDto.getCity());
        user.setCountry(userDto.getCountry());
        this.setDefaultMoney(user);
        this.setDefaultPicture(user);
        this.setEncodedPassword(user);
        this.setDefaultPicture(user);
        this.setDefaultRole(user);
        return user;
    }

    private void setDefaultRole(User user) {
        List<Role> roles = new ArrayList<>();
        roles.add(new Role(2, "ROLE_USER"));
        user.setRoles(roles);
    }

    private void setDefaultMoney(User user) {
        user.setMoney(0.0);
    }

    private void setDefaultPicture(User user) {
        user.setProfilePicture(rootImageUrl + defaultPictureName);
    }

    private void setEncodedPassword(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }
}
