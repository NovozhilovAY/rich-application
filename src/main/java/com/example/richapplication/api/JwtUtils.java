package com.example.richapplication.api;

import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;

public interface JwtUtils {

    String generateJwtToken(UserDetails userDetails);

    boolean validateJwtToken(String jwt);

    String parseJwt(HttpServletRequest request);

    String getUsernameFromToken(String jwt);
}
