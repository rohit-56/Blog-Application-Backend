package com.example.BloggerApp.service;

import com.example.BloggerApp.http.request.TagRequest;
import com.example.BloggerApp.http.request.UpdateBlogRequest;
import com.example.BloggerApp.models.BlogEntity;
import com.example.BloggerApp.models.TagEntity;
import com.example.BloggerApp.repository.BlogRepository;
import com.example.BloggerApp.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class BlogService {

    private BlogRepository blogRepository;

    private TagRepository tagRepository;

    public BlogService(BlogRepository blogRepository,TagRepository tagRepository){
        this.blogRepository = blogRepository;
        this.tagRepository = tagRepository;
    }
    public BlogEntity addBlog(BlogEntity blogEntity){
        return blogRepository.save(blogEntity);
    }

    public List<BlogEntity> getBlogEntities(){
        return blogRepository.findAll();
    }

    public BlogEntity getBlogById(long id){
        return blogRepository.getById(id);
    }

    public void deleteBlogById(long id){
        blogRepository.deleteById(id);
    }

    public void updateBlogEntityId(UpdateBlogRequest updateBlogRequest){
        BlogEntity blogEntity = blogRepository.getById(updateBlogRequest.getId());
        blogEntity.getTagEntities().clear();
        blogEntity.setTitle(updateBlogRequest.getTitle());
        blogEntity.setBody(updateBlogRequest.getBody());
        blogEntity.setImageCover(updateBlogRequest.getImageCover());
        blogEntity.setSubtitle(updateBlogRequest.getSubtitle());
       // List<TagEntity> tagEntities = updateBlogRequest.getTagRequests().stream()
        blogEntity.getTagEntities().addAll(updateBlogRequest.getTagRequests().stream().map(fromTagRequestToTagEntity).collect(Collectors.toList()));
        blogRepository.save(blogEntity);
    }

    private final Function<TagRequest,TagEntity> fromTagRequestToTagEntity =
            tagRequest -> {
          TagEntity tagEntity = new TagEntity();
          tagEntity.setTag(tagRequest.getTag());
          return tagEntity;
            };
}
