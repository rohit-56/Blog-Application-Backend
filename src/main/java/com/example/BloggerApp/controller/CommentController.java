package com.example.BloggerApp.controller;

import com.example.BloggerApp.http.request.CommentRequest;
import com.example.BloggerApp.http.request.UpdateCommentRequest;
import com.example.BloggerApp.http.response.GetCommentResponse;
import com.example.BloggerApp.service.impl.CommentServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
@SecurityRequirement(name = "Bearer Authentication")
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;

    @PostMapping("/create/blog/{blogId}")
    public ResponseEntity<GetCommentResponse> addComment(@RequestBody CommentRequest commentRequest, @PathVariable("blogId") Long blogId){
        GetCommentResponse comment = commentService.createComment(commentRequest,blogId);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @PutMapping("/update/blog/{blogId}")
    public ResponseEntity<GetCommentResponse> updateComment(@RequestBody UpdateCommentRequest updateCommentRequest,@PathVariable("blogId") Long blogId){
        GetCommentResponse getCommentResponse = commentService.updateComment(updateCommentRequest,blogId);
        return new ResponseEntity<>(getCommentResponse,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCommentById(@PathVariable("id") Long id){
        commentService.deleteCommentById(id);
        return new ResponseEntity<>("Comment Deleted",HttpStatus.OK);
    }
}
