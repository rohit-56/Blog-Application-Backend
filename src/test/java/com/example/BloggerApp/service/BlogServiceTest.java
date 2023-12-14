package com.example.BloggerApp.service;

import com.example.BloggerApp.models.BlogEntity;
import com.example.BloggerApp.models.CategoryEntity;
import com.example.BloggerApp.models.TagEntity;
import com.example.BloggerApp.models.UserEntity;
import com.example.BloggerApp.repository.BlogRepository;
import com.example.BloggerApp.repository.CategoryRepository;
import com.example.BloggerApp.repository.UserRepository;
import com.example.BloggerApp.service.impl.BlogServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class BlogServiceTest {

    @Mock
    private BlogRepository blogRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private BlogServiceImpl blogService;

     BlogServiceTest(){
        openMocks(this);
    }

    @Test
    public void addBlog_ValidInput_Success(){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("rohit-56");
        userEntity.setPassword("password");
        userEntity.setImage("url-image");
        userEntity.setBio("Hey This is Rohit");
        userEntity.setEmail("rohit@gmail.com");
        userEntity.setCreatedDate(new Date());

        UserEntity savedUser = new UserEntity();
        savedUser.setId(11L);
        savedUser.setUsername("rohit-56");
        savedUser.setPassword("password");
        savedUser.setImage("url-image");
        savedUser.setBio("Hey This is Rohit");
        savedUser.setEmail("rohit@gmail.com");
        savedUser.setCreatedDate(new Date());

        when(userRepository.save(userEntity)).thenReturn(savedUser);


        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setTitle("Development");

        CategoryEntity savedCategoryEntity = new CategoryEntity();
        savedCategoryEntity.setId(12L);
        savedCategoryEntity.setTitle("Development");

        when(categoryRepository.save(categoryEntity)).thenReturn(savedCategoryEntity);
        when(userRepository.findById(11L)).thenReturn(Optional.of(savedUser));
        when(categoryRepository.findById(12L)).thenReturn(Optional.of(savedCategoryEntity));

        BlogEntity blogEntity = new BlogEntity();
        blogEntity.setSubtitle("Blog-Subtitle");
        blogEntity.setBody("This is test blog entity body");
        blogEntity.setImageCover("Test image cover");
        blogEntity.setTitle("DSA");
        blogEntity.setCreatedAt(LocalDateTime.now());

        List<TagEntity> list = new ArrayList<>();
        TagEntity tagEntity = new TagEntity();
        tagEntity.setTag("dev");
        list.add(tagEntity);

        blogEntity.setTagEntities(list);

        blogEntity.setCategoryEntity(savedCategoryEntity);
        blogEntity.setUserEntity(savedUser);

        BlogEntity savedBlogEntity = new BlogEntity();
        savedBlogEntity.setId(1L);
        savedBlogEntity.setSubtitle("Blog-Subtitle");
        savedBlogEntity.setBody("This is test blog entity body");
        savedBlogEntity.setImageCover("Test image cover");
        savedBlogEntity.setTitle("DSA");
        savedBlogEntity.setCreatedAt(LocalDateTime.now());

        List<TagEntity> list1 = new ArrayList<>();
        TagEntity tagEntity1 = new TagEntity();
        tagEntity1.setTag("dev");
        list1.add(tagEntity1);

        savedBlogEntity.setTagEntities(list1);

        savedBlogEntity.setCategoryEntity(savedCategoryEntity);
        savedBlogEntity.setUserEntity(savedUser);

        when(blogRepository.save(blogEntity)).thenReturn(savedBlogEntity);

        BlogEntity resultBlogEntity = blogService.addBlog(blogEntity,11L,12L);

        assertEquals(resultBlogEntity.getTitle(),blogEntity.getTitle());
       // assertEquals(resultBlogEntity.getCreatedAt(),blogEntity.getCreatedAt());

    }

    @Test
    public void getBlogById_ValidInput_Success(){

        UserEntity savedUser = new UserEntity();
        savedUser.setId(11L);
        savedUser.setUsername("rohit-56");
        savedUser.setPassword("password");
        savedUser.setImage("url-image");
        savedUser.setBio("Hey This is Rohit");
        savedUser.setEmail("rohit@gmail.com");
        savedUser.setCreatedDate(new Date());

        CategoryEntity savedCategoryEntity = new CategoryEntity();
        savedCategoryEntity.setId(12L);
        savedCategoryEntity.setTitle("Development");


        BlogEntity savedBlogEntity = new BlogEntity();
        savedBlogEntity.setId(1L);
        savedBlogEntity.setSubtitle("Blog-Subtitle");
        savedBlogEntity.setBody("This is test blog entity body");
        savedBlogEntity.setImageCover("Test image cover");
        savedBlogEntity.setTitle("DSA");
        savedBlogEntity.setCreatedAt(LocalDateTime.now());

        List<TagEntity> list1 = new ArrayList<>();
        TagEntity tagEntity1 = new TagEntity();
        tagEntity1.setTag("dev");
        list1.add(tagEntity1);

        savedBlogEntity.setTagEntities(list1);

        savedBlogEntity.setCategoryEntity(savedCategoryEntity);
        savedBlogEntity.setUserEntity(savedUser);

        when(blogRepository.getById(1L)).thenReturn(savedBlogEntity);

        BlogEntity resultBlogEntity = blogService.getBlogById(1L);

        assertEquals(resultBlogEntity.getTitle(),savedBlogEntity.getTitle());
        assertEquals(resultBlogEntity.getCreatedAt(),savedBlogEntity.getCreatedAt());
    }

    @Test
    public void getListOfBlogEntities_ValidInput_Success(){

    }

    @Test
    public void deleteById_ValidInput_Success(){
        UserEntity savedUser = new UserEntity();
        savedUser.setId(11L);
        savedUser.setUsername("rohit-56");
        savedUser.setPassword("password");
        savedUser.setImage("url-image");
        savedUser.setBio("Hey This is Rohit");
        savedUser.setEmail("rohit@gmail.com");
        savedUser.setCreatedDate(new Date());

        CategoryEntity savedCategoryEntity = new CategoryEntity();
        savedCategoryEntity.setId(12L);
        savedCategoryEntity.setTitle("Development");


        BlogEntity savedBlogEntity = new BlogEntity();
        savedBlogEntity.setId(1L);
        savedBlogEntity.setSubtitle("Blog-Subtitle");
        savedBlogEntity.setBody("This is test blog entity body");
        savedBlogEntity.setImageCover("Test image cover");
        savedBlogEntity.setTitle("DSA");
        savedBlogEntity.setCreatedAt(LocalDateTime.now());

        List<TagEntity> list1 = new ArrayList<>();
        TagEntity tagEntity1 = new TagEntity();
        tagEntity1.setTag("dev");
        list1.add(tagEntity1);

        savedBlogEntity.setTagEntities(list1);

        savedBlogEntity.setCategoryEntity(savedCategoryEntity);
        savedBlogEntity.setUserEntity(savedUser);
      when(blogRepository.deleteById(1L)).thenReturn(savedBlogEntity);

      BlogEntity resultBlogEntity = blogService.deleteBlogById(1L);

        assertEquals(resultBlogEntity.getTitle(),savedBlogEntity.getTitle());
        assertEquals(resultBlogEntity.getCreatedAt(),savedBlogEntity.getCreatedAt());
    }

    @Test
    public void updateBlogEntity_ValidInput_Success(){

    }
}
