package com.example.BloggerApp.service.impl;

import com.example.BloggerApp.configurations.AppConstants;
import com.example.BloggerApp.http.request.CreateUser;
import com.example.BloggerApp.http.response.GetUserResponse;
import com.example.BloggerApp.models.Roles;
import com.example.BloggerApp.models.UserEntity;
import com.example.BloggerApp.repository.RoleRepository;
import com.example.BloggerApp.repository.UserRepository;
import com.example.BloggerApp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserEntity createUser(UserEntity userEntity){
    return userRepository.save(userEntity);
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

    @Override
    public void deleteUseryId(Long id) {
        userRepository.deleteById(id);
    }

    public GetUserResponse registerUser(CreateUser createUser){
        UserEntity userEntity = modelMapper.map(createUser, UserEntity.class);

        //Encoding User Password
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

        Roles roles = roleRepository.findById(AppConstants.NORMAL_USER).get();
        //Adding Role of New User
        userEntity.getRoles().add(roles);

        userEntity.setCreatedDate(new Date());

        UserEntity savedUserEntity = userRepository.save(userEntity);

        GetUserResponse getUserResponse = modelMapper.map(savedUserEntity, GetUserResponse.class);

        return getUserResponse;

    }
}
