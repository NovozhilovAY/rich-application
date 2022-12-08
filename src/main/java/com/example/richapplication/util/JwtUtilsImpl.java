package com.example.richapplication.util;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.richapplication.api.JwtUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

@Component
public class JwtUtilsImpl implements JwtUtils {

    @Value("${jwt.token.secret}")
    private String jwtSecret;

    @Override
    public String generateJwtToken(UserDetails userDetails) {
        Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes(StandardCharsets.UTF_8));
        return JWT.create().withSubject(userDetails.getUsername()).sign(algorithm);
    }

    @Override
    public boolean validateJwtToken(String jwt) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes(StandardCharsets.UTF_8));
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(jwt);
            return true;
        }catch (JWTVerificationException exception){
            exception.printStackTrace();
            return false;
        }
    }

    @Override
    public String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if(StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")){
            return headerAuth.substring(7);
        }
        return null;
    }

    @Override
    public String getUsernameFromToken(String jwt) {
        DecodedJWT decodedJwt = JWT.decode(jwt);
        return decodedJwt.getSubject();
    }
}
