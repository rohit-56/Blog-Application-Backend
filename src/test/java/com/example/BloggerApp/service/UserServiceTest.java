package com.example.BloggerApp.service;

import com.example.BloggerApp.models.UserEntity;
import com.example.BloggerApp.repository.UserRepository;
import com.example.BloggerApp.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    UserServiceTest() {
        openMocks(this);
    }

    @Test
    public void create_User_Test() throws Exception{
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("rohit-56");
        userEntity.setPassword("password");
        userEntity.setImage("url-image");
        userEntity.setBio("Hey This is Rohit");
        userEntity.setEmail("rohit@gmail.com");
        userEntity.setCreatedDate(new Date());

        UserEntity savedUser = new UserEntity();
        savedUser.setId(1L);
        savedUser.setUsername("rohit-56");
        savedUser.setPassword("password");
        savedUser.setImage("url-image");
        savedUser.setBio("Hey This is Rohit");
        savedUser.setEmail("rohit@gmail.com");
        savedUser.setCreatedDate(new Date());


        when(userRepository.save(userEntity)).thenReturn(savedUser);

        UserEntity resultUserEntity = userService.createUser(userEntity);

        assertEquals(resultUserEntity.getUsername() , userEntity.getUsername());
    }

    @Test
    public void get_UserEntity_By_Id_Test(){
        UserEntity savedUser = new UserEntity();
        savedUser.setId(1L);
        savedUser.setUsername("rohit-56");
        savedUser.setPassword("password");
        savedUser.setImage("url-image");
        savedUser.setBio("Hey This is Rohit");
        savedUser.setEmail("rohit@gmail.com");
        savedUser.setCreatedDate(new Date());

        when(userRepository.getById(1L)).thenReturn(savedUser);

        UserEntity resultUserEntity = userService.getUserById(1L);

        assertEquals(resultUserEntity.getUsername(),savedUser.getUsername());
        assertEquals(resultUserEntity.getCreatedDate(),savedUser.getCreatedDate());
    }

    @Test
    public void get_List_Of_User_Entity_Test(){
        UserEntity userEntity1 = new UserEntity();
        userEntity1.setUsername("rohit-56");
        userEntity1.setPassword("password");
        userEntity1.setImage("url-image");
        userEntity1.setBio("Hey This is Rohit");
        userEntity1.setEmail("rohit@gmail.com");
        userEntity1.setCreatedDate(new Date());

        UserEntity userEntity2 = new UserEntity();
        userEntity2.setUsername("test-56");
        userEntity2.setPassword("password-test");
        userEntity2.setImage("url-image-test");
        userEntity2.setBio("Hey This is Test User");
        userEntity2.setEmail("test@gmail.com");
        userEntity2.setCreatedDate(new Date());

       List<UserEntity> list = new ArrayList<>();
       list.add(userEntity1);
       list.add(userEntity2);
       int pagenumber=0,limit=1;
        Pageable pageable = PageRequest.of(pagenumber,limit);
        Page<UserEntity> userEntityPage =  Mockito.mock(Page.class);

       when(userRepository.findAll(pageable)).thenReturn(userEntityPage);

       when(userService.getAllUsers(pagenumber,limit)).thenReturn(list);

       //assertEquals(users.get(0).getUsername(),list.get(0).getUsername());
       //assertEquals(users.get(0).getCreatedDate(),list.get(0).getCreatedDate());
    }




}
