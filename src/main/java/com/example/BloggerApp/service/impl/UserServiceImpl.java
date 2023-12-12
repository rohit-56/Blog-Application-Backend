package com.example.BloggerApp.service.impl;

import com.example.BloggerApp.models.UserEntity;
import com.example.BloggerApp.repository.UserRepository;
import com.example.BloggerApp.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void createUser(UserEntity userEntity){
    userRepository.save(userEntity);
    }

    public List<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }

    public void updateUserDetails(UserEntity userEntity){
        userRepository.updateUser(userEntity.getUsername(), userEntity.getEmail(), userEntity.getImage(), userEntity.getBio(), userEntity.getId());
    }

    public UserEntity getUserById(Long id){
        return userRepository.getById(id);
    }
}
