package com.example.BloggerApp.controller;

import com.example.BloggerApp.http.request.CreateUser;
import com.example.BloggerApp.http.response.GetUserResponse;
import com.example.BloggerApp.models.UserEntity;
import com.example.BloggerApp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody CreateUser createUser){
        userService.createUser(fromUserRequestToUserEntity.apply(createUser));
        return new ResponseEntity<>("User Created",HttpStatus.CREATED);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<GetUserResponse>> getAllUser(){
        var usersList = userService.getAllUsers();
        return new ResponseEntity<>(usersList.stream().map(fromUserEntityToUserResponse).collect(Collectors.toList()),HttpStatus.OK);
    }

    private final Function<CreateUser,UserEntity> fromUserRequestToUserEntity =
            createUser -> {
             var userEntity = new UserEntity();
             userEntity.setUsername(createUser.getUsername());
             userEntity.setEmail(createUser.getEmail());
             userEntity.setPassword(createUser.getPassword());
             return userEntity;
            };

    private final Function<UserEntity,GetUserResponse> fromUserEntityToUserResponse =
        userEntity-> {
           var userResponse = new GetUserResponse();
           userResponse.setId(userEntity.getId());
           userResponse.setUsername(userEntity.getUsername());
           userResponse.setEmail(userEntity.getEmail());
           userResponse.setPassword(userEntity.getPassword());
           return userResponse;
            };

}
