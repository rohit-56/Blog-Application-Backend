package com.example.BloggerApp.controller;

import com.example.BloggerApp.configurations.JWTTokenHelper;
import com.example.BloggerApp.http.request.LoginRequest;
import com.example.BloggerApp.http.response.LoginResponse;
import com.example.BloggerApp.service.impl.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    private JWTTokenHelper jwtTokenHelper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> createToken(@RequestBody LoginRequest loginRequest){
        this.authenticate(loginRequest.getUsername(),loginRequest.getPassword());

        UserDetails userDetails = customUserDetailService.loadUserByUsername(loginRequest.getUsername());

        String token = jwtTokenHelper.generateToken(userDetails);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(token);
        return new ResponseEntity<>(loginResponse, HttpStatus.CREATED);
    }

    private void authenticate(String email, String password) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(email,password);

        try{
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        }
        catch (Exception e){
            System.out.println("Unable to Authorize Request");
        }
    }
}
