package com.example.BloggerApp.service;

import com.example.BloggerApp.http.request.UpdateBlogRequest;
import com.example.BloggerApp.models.BlogEntity;
import com.example.BloggerApp.models.TagEntity;

import java.util.List;

public interface BlogService {

    BlogEntity addBlog(BlogEntity blogEntity, long userid, long categoryId);

    List<BlogEntity> getBlogEntities(int pageNumber,int limit,String sortBy);

    BlogEntity getBlogById(long id);

    BlogEntity deleteBlogById(long id);

    BlogEntity updateBlogEntityId(UpdateBlogRequest updateBlogRequest);

    List<TagEntity> getTagList(long blogId);

    List<BlogEntity> getBlogEntitiesByCategoryId(Long categoryId);

    List<BlogEntity> getBlogEntitiesByUserId(Long userId);
}
