package com.example.BloggerApp.service.impl;

import com.example.BloggerApp.http.request.TagRequest;
import com.example.BloggerApp.http.request.UpdateBlogRequest;
import com.example.BloggerApp.models.BlogEntity;
import com.example.BloggerApp.models.CategoryEntity;
import com.example.BloggerApp.models.TagEntity;
import com.example.BloggerApp.models.UserEntity;
import com.example.BloggerApp.repository.BlogRepository;
import com.example.BloggerApp.repository.CategoryRepository;
import com.example.BloggerApp.repository.UserRepository;
import com.example.BloggerApp.service.BlogService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class BlogServiceImpl implements BlogService {

    private BlogRepository blogRepository;

    private UserRepository userRepository;

    private CategoryRepository categoryRepository;

    public BlogServiceImpl(BlogRepository blogRepository, UserRepository userRepository, CategoryRepository categoryRepository){
        this.blogRepository = blogRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }
    public BlogEntity addBlog(BlogEntity blogEntity,long userid,long categoryId){
        UserEntity userEntity = userRepository.findById(userid).orElseThrow(() -> new RuntimeException("User Not Found"));
        CategoryEntity categoryEntity = categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Category Not Found"));
        blogEntity.setUserEntity(userEntity);
        blogEntity.setCategoryEntity(categoryEntity);
        return blogRepository.save(blogEntity);
    }

    public List<BlogEntity> getBlogEntities(int pageNumber,int limit,String sortBy){
        Pageable pageable = PageRequest.of(pageNumber,limit, Sort.by(sortBy));
        List<BlogEntity> blogEntityList = blogRepository.findAll(pageable).getContent();
        return  blogEntityList;
    }

    public BlogEntity getBlogById(long id){
        return blogRepository.getById(id);
    }

    public BlogEntity deleteBlogById(long id){
        return blogRepository.deleteById(id);
    }

    public BlogEntity updateBlogEntityId(UpdateBlogRequest updateBlogRequest){
        BlogEntity blogEntity = blogRepository.getById(updateBlogRequest.getId());
        blogEntity.getTagEntities().clear();
        blogEntity.setTitle(updateBlogRequest.getTitle());
        blogEntity.setBody(updateBlogRequest.getBody());
        blogEntity.setImageCover(updateBlogRequest.getImageCover());
        blogEntity.setSubtitle(updateBlogRequest.getSubtitle());
        blogEntity.getTagEntities().addAll(updateBlogRequest.getTagRequests().stream().map(fromTagRequestToTagEntity).collect(Collectors.toList()));
       return blogRepository.save(blogEntity);
    }

    public List<TagEntity> getTagList(long blogId){
        BlogEntity blogEntity = blogRepository.getById(blogId);
        return blogEntity.getTagEntities();
    }

    @Override
    public List<BlogEntity> getBlogEntitiesByCategoryId(Long categoryId) {
        CategoryEntity categoryEntity = categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("No category exist with this id:"+categoryId));
        return blogRepository.getBlogByCategoryEntity(categoryEntity);
    }

    @Override
    public List<BlogEntity> getBlogEntitiesByUserId(Long userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("No user found with this id:"+userId));
        return blogRepository.getBlogByUserEntity(userEntity);
    }

    private final Function<TagRequest,TagEntity> fromTagRequestToTagEntity =
            tagRequest -> {
          TagEntity tagEntity = new TagEntity();
          tagEntity.setTag(tagRequest.getTag());
          return tagEntity;
            };
}
