package com.example.richapplication.service;

import com.example.richapplication.api.AuthService;
import com.example.richapplication.api.JwtUtils;
import com.example.richapplication.dto.AuthRequest;
import com.example.richapplication.dto.AuthResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtils jwtUtils;

    public AuthServiceImpl(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public ResponseEntity<AuthResponse> login(AuthRequest request) {
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword());
        Authentication authentication = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails user = (UserDetails) authentication.getPrincipal();
        String jwt = jwtUtils.generateJwtToken(user);
        List<String> roles = this.getListOfAuthorities(user);
        AuthResponse response = new AuthResponse(user.getUsername(), jwt, roles);
        return new  ResponseEntity<>(response, HttpStatus.OK);
    }

    private List<String> getListOfAuthorities(UserDetails userDetails){
        List<String> listOfAuthorities = new ArrayList<>();
        userDetails.getAuthorities().forEach(
                grantedAuthority -> listOfAuthorities.add(grantedAuthority.toString())
        );
        return listOfAuthorities;
    }
}
