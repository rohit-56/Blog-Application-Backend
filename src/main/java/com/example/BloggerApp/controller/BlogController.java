package com.example.BloggerApp.controller;

import com.example.BloggerApp.http.request.CreateBlogRequest;
import com.example.BloggerApp.http.request.TagRequest;
import com.example.BloggerApp.http.request.UpdateBlogRequest;
import com.example.BloggerApp.http.response.GetBlogResponse;
import com.example.BloggerApp.http.response.GetTagResponse;
import com.example.BloggerApp.models.BlogEntity;
import com.example.BloggerApp.models.TagEntity;
import com.example.BloggerApp.service.BlogService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/blog")
public class BlogController {

    private BlogService blogService;

    public BlogController(BlogService blogService){
        this.blogService = blogService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createBlog(@RequestBody CreateBlogRequest createBlogRequest){
      blogService.addBlog(fromBlogRequestToBlogModel.apply(createBlogRequest));
      return new ResponseEntity<>("Blog Created",HttpStatus.CREATED);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<GetBlogResponse>> getAllBlogs(){
        List<BlogEntity> blogEntityList = blogService.getBlogEntities();
        return new ResponseEntity<>(blogEntityList.stream().map(fromBlogModelToBlogResponse).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<GetBlogResponse> getBlogById(@PathVariable("id") long id){
        BlogEntity blogEntity = blogService.getBlogById(id);
        return new ResponseEntity<>(fromBlogModelToBlogResponse.apply(blogEntity),HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<GetBlogResponse> updateBlog(@PathVariable("id") long id, @RequestBody UpdateBlogRequest updateBlogRequest){
        BlogEntity blogEntity = blogService.updateBlogEntityId(updateBlogRequest);
        return new ResponseEntity<>(fromBlogModelToBlogResponse.apply(blogEntity),HttpStatus.ACCEPTED);

    }
    @GetMapping("/get/tagList/{blogId}")
    public ResponseEntity<List<GetTagResponse>> getTagsList(@PathVariable("blogId") long blogId){
        List<TagEntity> tagEntityList = blogService.getTagList(blogId);
        return new ResponseEntity<>(tagEntityList.stream().map(fromTagModelToTagResponse).collect(Collectors.toList()),HttpStatus.OK);
    }

    private final Function<BlogEntity,GetBlogResponse> fromBlogModelToBlogResponse = this :: apply;
    private GetBlogResponse apply(BlogEntity blogEntity){
             GetBlogResponse getBlogResponse = new GetBlogResponse();
             getBlogResponse.setId(blogEntity.getId());
             getBlogResponse.setTitle(blogEntity.getTitle());
             getBlogResponse.setSubtitle(blogEntity.getSubtitle());
             getBlogResponse.setGetTagResponses(blogEntity.getTagEntities().stream().map(fromTagModelToTagResponse).collect(Collectors.toList()));
             getBlogResponse.setBody(blogEntity.getBody());
             getBlogResponse.setImageCover(blogEntity.getImageCover());
             getBlogResponse.setCreatedAt(blogEntity.getCreatedAt());
             return getBlogResponse;
            }

    private final Function<CreateBlogRequest, BlogEntity> fromBlogRequestToBlogModel =
            this::apply;

    private final Function<TagRequest, TagEntity> fromTagRequestToTagModel =
            tagRequest -> {
            TagEntity tagEntity = new TagEntity();
            tagEntity.setTag(tagRequest.getTag());
            return tagEntity;
            };

    private final Function<TagEntity, GetTagResponse> fromTagModelToTagResponse =
            tagEntity -> {
             GetTagResponse getTagResponse = new GetTagResponse(tagEntity.getId(),tagEntity.getTag());
             return getTagResponse;
            };

    private BlogEntity apply(CreateBlogRequest createBlogRequest) {
      BlogEntity blogEntity = new BlogEntity();
      blogEntity.setTitle(createBlogRequest.getTitle());
      blogEntity.setSubtitle(createBlogRequest.getSubtitle());
      blogEntity.setBody(createBlogRequest.getBody());
      blogEntity.setTagEntities(createBlogRequest.getTagRequests().stream().map(fromTagRequestToTagModel).collect(Collectors.toList()));
      blogEntity.setImageCover(createBlogRequest.getImageCover());
      blogEntity.setCreatedAt(LocalDateTime.now());
        return blogEntity;
    }
}
