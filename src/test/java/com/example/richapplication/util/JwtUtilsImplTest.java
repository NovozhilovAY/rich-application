package com.example.richapplication.util;

import com.example.richapplication.api.JwtUtils;
import com.example.richapplication.model.Role;
import com.example.richapplication.security.SecurityUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@SpringBootTest
class JwtUtilsImplTest {

    @Autowired
    private JwtUtils jwtUtils;

    @Test
    void generateJwtTokenWorks() {
        UserDetails userDetails = new SecurityUser("test", "test", new ArrayList<>());
        Assertions.assertDoesNotThrow(()->jwtUtils.generateJwtToken(userDetails));
    }

    @Test
    void generateJwtTokenReturnsString() {
        UserDetails userDetails = new SecurityUser("test", "test", new ArrayList<>());
        Object jwt = jwtUtils.generateJwtToken(userDetails);
        Assertions.assertTrue(jwt instanceof String);
    }

    @Test
    void generateJwtTokenDoesNotReturnNull() {
        UserDetails userDetails = new SecurityUser("test", "test", new ArrayList<>());
        String jwt = jwtUtils.generateJwtToken(userDetails);
        Assertions.assertNotNull(jwt);
    }

    @Test
    void generateJwtTokenWorksCorrectly() {
        UserDetails userDetails = new SecurityUser("login1", "admin", new ArrayList<>());
        String jwt = jwtUtils.generateJwtToken(userDetails);
        Assertions.assertEquals("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJsb2dpbjEifQ.F3YxWOYQ4ZNVch-0W9wThtEO8XeAvhSSfreTSfPiauo", jwt);
    }

    @Test
    void validateJwtTokenWorks() {
        Assertions.assertDoesNotThrow(()->jwtUtils.validateJwtToken("some token"));
    }

    @Test
    void validateJwtTokenReturnsTrue() {
        String validToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJsb2dpbjEifQ.F3YxWOYQ4ZNVch-0W9wThtEO8XeAvhSSfreTSfPiauo";

        boolean isTokenValid = jwtUtils.validateJwtToken(validToken);

        Assertions.assertTrue(isTokenValid);
    }

    @Test
    void validateJwtTokenReturnsFalse() {
        String invalidToken = "invalidToken";

        boolean isTokenValid = jwtUtils.validateJwtToken(invalidToken);

        Assertions.assertFalse(isTokenValid);
    }


    @Test
    void getUsernameFromTokenWorks() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJsb2dpbjEifQ.F3YxWOYQ4ZNVch-0W9wThtEO8XeAvhSSfreTSfPiauo";

        Assertions.assertDoesNotThrow(()-> jwtUtils.getUsernameFromToken(token));
    }

    @Test
    void getUsernameFromTokenDoesNotReturnNull() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJsb2dpbjEifQ.F3YxWOYQ4ZNVch-0W9wThtEO8XeAvhSSfreTSfPiau";

        String username = jwtUtils.getUsernameFromToken(token);

        Assertions.assertNotNull(username);
    }

    @Test
    void getUsernameFromTokenReturnsUsername() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJsb2dpbjEifQ.F3YxWOYQ4ZNVch-0W9wThtEO8XeAvhSSfreTSfPiau";

        String username = jwtUtils.getUsernameFromToken(token);

        Assertions.assertEquals("login1", username);
    }

}