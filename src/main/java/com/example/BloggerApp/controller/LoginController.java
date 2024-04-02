package com.example.BloggerApp.controller;

import com.example.BloggerApp.configurations.JWTTokenHelper;
import com.example.BloggerApp.http.request.CreateUser;
import com.example.BloggerApp.http.request.LoginRequest;
import com.example.BloggerApp.http.response.GetUserResponse;
import com.example.BloggerApp.http.response.LoginResponse;
import com.example.BloggerApp.models.UserEntity;
import com.example.BloggerApp.service.UserService;
import com.example.BloggerApp.service.impl.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
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

    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> createToken(@RequestBody LoginRequest loginRequest){
        this.authenticate(loginRequest.getUsername(),loginRequest.getPassword());
        UserDetails userDetails = customUserDetailService.loadUserByUsername(loginRequest.getUsername());

        String token = jwtTokenHelper.generateToken(userDetails);
        UserEntity userEntity = userService.getUserEntityByUsername(loginRequest.getUsername());
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(token);
        loginResponse.setUserId(userEntity.getId());
        loginResponse.setUsername(userEntity.getUsername());
        loginResponse.setEmail(userEntity.getEmail());
        loginResponse.setImage(userEntity.getImage());
        loginResponse.setCreatedDate(userEntity.getCreatedDate());
        loginResponse.setAbout(userEntity.getBio());
        loginResponse.setRoles(userEntity.getRoles());
        return new ResponseEntity<>(loginResponse, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/register")
    public ResponseEntity<GetUserResponse> registerUser(@RequestBody CreateUser createUser){
        GetUserResponse getUserResponse = userService.registerUser(createUser);
        return new ResponseEntity<>(getUserResponse,HttpStatus.CREATED);
    }

    private void authenticate(String email, String password) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(email,password);

        try{
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        }
        catch (Exception e){
            System.out.println("Unable to Authorize Request");
            throw new NullPointerException("Username or Password not found");
        }
    }
}
