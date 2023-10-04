package com.example.BloggerApp.service;

import com.example.BloggerApp.models.UserEntity;
import com.example.BloggerApp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void createUser(UserEntity userEntity){
    userRepository.save(userEntity);
    }

    public List<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }
}
