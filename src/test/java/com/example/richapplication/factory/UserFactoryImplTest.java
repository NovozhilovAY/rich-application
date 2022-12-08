package com.example.richapplication.factory;

import com.example.richapplication.dto.RegistrationUserDto;
import com.example.richapplication.model.Role;
import com.example.richapplication.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class UserFactoryImplTest {

    @Value("${root.image.url}")
    private String rootImageUrl;

    @Value("${default.picture.name}")
    private String defaultPictureName;

    @Autowired
    private UserFactoryImpl userFactory;


    @Test
    void createUserWorks() {
        RegistrationUserDto userDto = new RegistrationUserDto("login", "firstName", "lastName" , "country", "city", "password");

        Assertions.assertDoesNotThrow(()->userFactory.createUser(userDto));
    }

    @Test
    void createUserSetsLoginCorrectly() {
        RegistrationUserDto userDto = new RegistrationUserDto("login", "firstName", "lastName" , "country", "city", "password");

        User user = userFactory.createUser(userDto);

        Assertions.assertEquals(userDto.getLogin(), user.getLogin());
    }

    @Test
    void createUserSetsFirstNameCorrectly() {
        RegistrationUserDto userDto = new RegistrationUserDto("login", "firstName", "lastName" , "country", "city", "password");

        User user = userFactory.createUser(userDto);

        Assertions.assertEquals(userDto.getFirstName(), user.getFirstName());
    }

    @Test
    void createUserSetsLastNameCorrectly() {
        RegistrationUserDto userDto = new RegistrationUserDto("login", "firstName", "lastName" , "country", "city", "password");

        User user = userFactory.createUser(userDto);

        Assertions.assertEquals(userDto.getLastName(), user.getLastName());
    }

    @Test
    void createUserSetsCityCorrectly() {
        RegistrationUserDto userDto = new RegistrationUserDto("login", "firstName", "lastName" , "country", "city", "password");

        User user = userFactory.createUser(userDto);

        Assertions.assertEquals(userDto.getCity(), user.getCity());
    }

    @Test
    void createUserSetsCountryCorrectly() {
        RegistrationUserDto userDto = new RegistrationUserDto("login", "firstName", "lastName" , "country", "city", "password");

        User user = userFactory.createUser(userDto);

        Assertions.assertEquals(userDto.getCountry(), user.getCountry());
    }

    @Test
    void createUserReturnsUser() {
        RegistrationUserDto userDto = new RegistrationUserDto("login", "firstName", "lastName" , "country", "city", "password");

        Object user = userFactory.createUser(userDto);

        Assertions.assertTrue(user instanceof User);
    }

    @Test
    void createUserDoesNotReturnNull() {
        RegistrationUserDto userDto = new RegistrationUserDto("login", "firstName", "lastName" , "country", "city", "password");

        User user = userFactory.createUser(userDto);

        Assertions.assertNotNull(user);
    }

    @Test
    void createUserSetsDefaultRoles() {
        RegistrationUserDto userDto = new RegistrationUserDto("login", "firstName", "lastName" , "country", "city", "password");
        List<Role> defaultRoles = new ArrayList<>();
        defaultRoles.add(new Role(2, "ROLE_USER"));

        User user = userFactory.createUser(userDto);


        Assertions.assertEquals(defaultRoles, user.getRoles());
    }

    @Test
    void createUserSetDefaultProfilePicture() {
        RegistrationUserDto userDto = new RegistrationUserDto("login", "firstName", "lastName" , "country", "city", "password");
        String defaultProfilePictureName = this.rootImageUrl + this.defaultPictureName;

        User user = userFactory.createUser(userDto);

        Assertions.assertEquals(defaultProfilePictureName, user.getProfilePicture());
    }

    @Test
    void createUserSetDefaultMoney() {
        RegistrationUserDto userDto = new RegistrationUserDto("login", "firstName", "lastName" , "country", "city", "password");
        Double defaultMoney = 0.0;

        User user = userFactory.createUser(userDto);

        Assertions.assertEquals(defaultMoney, user.getMoney());
    }

    @Test
    void createUserSetEncodedPassword() {
        RegistrationUserDto userDto = new RegistrationUserDto("login", "firstName", "lastName" , "country", "city", "password");
        String password = userDto.getPassword();

        User user = userFactory.createUser(userDto);

        Assertions.assertNotEquals(password, user.getPassword());
    }



}