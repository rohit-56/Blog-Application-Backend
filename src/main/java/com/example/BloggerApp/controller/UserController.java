package com.example.BloggerApp.controller;

import com.example.BloggerApp.http.request.CreateUser;
import com.example.BloggerApp.http.request.UpdateUser;
import com.example.BloggerApp.http.response.GetUserResponse;
import com.example.BloggerApp.models.UserEntity;
import com.example.BloggerApp.service.impl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl){
        this.userServiceImpl = userServiceImpl;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody CreateUser createUser){
        userServiceImpl.createUser(fromUserRequestToUserEntity.apply(createUser));
        return new ResponseEntity<>("User Created",HttpStatus.CREATED);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<GetUserResponse>> getAllUser(){
        var usersList = userServiceImpl.getAllUsers();
        return new ResponseEntity<>(usersList.stream().map(fromUserEntityToUserResponse).collect(Collectors.toList()),HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<GetUserResponse> updateUserDetails(@RequestBody UpdateUser updateUser){
        var userEntity =  fromUpdateUserRequestToUserEntity.apply(updateUser);
         userServiceImpl.updateUserDetails(userEntity);
         var updatedUserEntity = userServiceImpl.getUserById(updateUser.getId());
        return new ResponseEntity<>(fromUserEntityToUserResponse.apply(updatedUserEntity),HttpStatus.ACCEPTED);
    }

    private final Function<UpdateUser,UserEntity> fromUpdateUserRequestToUserEntity =
            updateUser -> {
            var userEntity = new UserEntity();
            userEntity.setId(updateUser.getId());
            userEntity.setUsername(updateUser.getUsername());
            userEntity.setPassword(updateUser.getPassword());
            userEntity.setEmail(updateUser.getEmail());
            userEntity.setImage(updateUser.getImage());
            userEntity.setBio(updateUser.getBio());
            return userEntity;
            };
    private final Function<CreateUser,UserEntity> fromUserRequestToUserEntity =
            createUser -> {
             var userEntity = new UserEntity();
             userEntity.setUsername(createUser.getUsername());
             userEntity.setEmail(createUser.getEmail());
             userEntity.setPassword(createUser.getPassword());
             userEntity.setImage(createUser.getImage());
             userEntity.setBio(createUser.getBio());
             userEntity.setCreatedDate(new Date());
             return userEntity;
            };

    private final Function<UserEntity,GetUserResponse> fromUserEntityToUserResponse =
        userEntity-> {
           var userResponse = new GetUserResponse();
           userResponse.setId(userEntity.getId());
           userResponse.setUsername(userEntity.getUsername());
           userResponse.setEmail(userEntity.getEmail());
           userResponse.setPassword(userEntity.getPassword());
           userResponse.setImage(userEntity.getImage());
           userResponse.setBio(userEntity.getBio());
           userResponse.setCreatedDate(userEntity.getCreatedDate());
           userResponse.setCreatedDate(userEntity.getCreatedDate());
           return userResponse;
            };

}
