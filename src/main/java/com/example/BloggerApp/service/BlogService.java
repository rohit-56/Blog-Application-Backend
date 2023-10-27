package com.example.BloggerApp.service;

import com.example.BloggerApp.models.BlogEntity;
import com.example.BloggerApp.repository.BlogRepository;
import com.example.BloggerApp.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    private BlogRepository blogRepository;

    private TagRepository tagRepository;

    public BlogService(BlogRepository blogRepository,TagRepository tagRepository){
        this.blogRepository = blogRepository;
        this.tagRepository = tagRepository;
    }
    public void addBlog(BlogEntity blogEntity){
        blogRepository.save(blogEntity);
    }

    public List<BlogEntity> getBlogEntities(){
        return blogRepository.findAll();
    }
}
