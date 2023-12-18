package com.example.BloggerApp.service;

import com.example.BloggerApp.models.UserEntity;

import java.util.List;

public interface UserService {

    UserEntity createUser(UserEntity userEntity);

    List<UserEntity> getAllUsers();

    void updateUserDetails(UserEntity userEntity);

    UserEntity getUserById(Long id);

    void deleteUseryId(Long id);


}
