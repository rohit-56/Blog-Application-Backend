package com.example.BloggerApp.service;

import com.example.BloggerApp.http.request.UpdateBlogRequest;
import com.example.BloggerApp.models.BlogEntity;
import com.example.BloggerApp.models.TagEntity;

import java.util.List;

public interface BlogService {

    BlogEntity addBlog(BlogEntity blogEntity, long userid, long categoryId);

    List<BlogEntity> getBlogEntities();

    BlogEntity getBlogById(long id);

    void deleteBlogById(long id);

    BlogEntity updateBlogEntityId(UpdateBlogRequest updateBlogRequest);

    List<TagEntity> getTagList(long blogId);
}
