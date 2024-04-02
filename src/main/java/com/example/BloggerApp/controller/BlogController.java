package com.example.BloggerApp.controller;

import com.example.BloggerApp.http.request.CreateBlogRequest;
import com.example.BloggerApp.http.request.TagRequest;
import com.example.BloggerApp.http.request.UpdateBlogRequest;
import com.example.BloggerApp.http.response.GetBlogFeedResponse;
import com.example.BloggerApp.http.response.GetBlogResponse;
import com.example.BloggerApp.http.response.GetTagResponse;
import com.example.BloggerApp.models.BlogEntity;
import com.example.BloggerApp.models.TagEntity;
import com.example.BloggerApp.service.impl.BlogServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/blog")
@SecurityRequirement(name = "Bearer Authentication")
public class BlogController {

    private BlogServiceImpl blogServiceImpl;

    public BlogController(BlogServiceImpl blogServiceImpl){
        this.blogServiceImpl = blogServiceImpl;
    }

    @CrossOrigin(origins = "http://localhost:3000",allowedHeaders = "*")
    @PostMapping("/create/user/{user_id}/category/{category_id}")
    public ResponseEntity<String> createBlog(@RequestBody CreateBlogRequest createBlogRequest,@PathVariable("user_id") long userId,@PathVariable("category_id") long categoryId){
      blogServiceImpl.addBlog(fromBlogRequestToBlogModel.apply(createBlogRequest),userId,categoryId);
      return new ResponseEntity<>("Blog Created",HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/get-all")
    public ResponseEntity<List<GetBlogResponse>> getAllBlogs(
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
            @RequestParam(value = "limit",defaultValue = "2",required = false) Integer limit,
            @RequestParam(value = "sortBy",defaultValue = "title",required = false) String sortBy
    ){
        List<BlogEntity> blogEntityList = blogServiceImpl.getBlogEntities(pageNumber,limit,sortBy);
        return new ResponseEntity<>(blogEntityList.stream().map(fromBlogModelToBlogResponse).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/get-all-info")
    public ResponseEntity<List<GetBlogFeedResponse>> getAllBlogsWithUserAndCategoryDetails(
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
            @RequestParam(value = "limit",defaultValue = "2",required = false) Integer limit
    ){
        return new ResponseEntity<>(blogServiceImpl.getBlogWithUserAndCategoryDetails(pageNumber,limit), HttpStatus.OK);
    }



    @GetMapping("/get/{id}")
    public ResponseEntity<GetBlogResponse> getBlogById(@PathVariable("id") long id){
        BlogEntity blogEntity = blogServiceImpl.getBlogById(id);
        return new ResponseEntity<>(fromBlogModelToBlogResponse.apply(blogEntity),HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<GetBlogResponse> updateBlog(@PathVariable("id") long id, @RequestBody UpdateBlogRequest updateBlogRequest){
        BlogEntity blogEntity = blogServiceImpl.updateBlogEntityId(updateBlogRequest);
        return new ResponseEntity<>(fromBlogModelToBlogResponse.apply(blogEntity),HttpStatus.ACCEPTED);

    }
    @GetMapping("/get/tagList/{blogId}")
    public ResponseEntity<List<GetTagResponse>> getTagsList(@PathVariable("blogId") long blogId){
        List<TagEntity> tagEntityList = blogServiceImpl.getTagList(blogId);
        return new ResponseEntity<>(tagEntityList.stream().map(fromTagModelToTagResponse).collect(Collectors.toList()),HttpStatus.OK);
    }

    @GetMapping("/get-by-categoryId/{categoryId}")
    public ResponseEntity<List<GetBlogResponse>> getBlogEntitesByCategoryId(@PathVariable("categoryId") Long categoryId){
        List<BlogEntity> blogEntityList = blogServiceImpl.getBlogEntitiesByCategoryId(categoryId);
        return new ResponseEntity<>(blogEntityList.stream().map(fromBlogModelToBlogResponse).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/get-by-userId/{userId}")
    public ResponseEntity<List<GetBlogResponse>> getBlogEntitesByUserId(@PathVariable("userId") Long userId){
     List<BlogEntity> blogEntityList = blogServiceImpl.getBlogEntitiesByUserId(userId);
     return new ResponseEntity<>(blogEntityList.stream().map(fromBlogModelToBlogResponse).collect(Collectors.toList()), HttpStatus.OK);
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
             getBlogResponse.setUserId(blogEntity.getUserEntity().getId());
             getBlogResponse.setCategoryId(blogEntity.getCategoryEntity().getId());
             getBlogResponse.setComments(blogEntity.getComments());
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
