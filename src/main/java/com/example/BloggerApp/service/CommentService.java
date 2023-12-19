package com.example.BloggerApp.service;

import com.example.BloggerApp.http.request.CommentRequest;
import com.example.BloggerApp.http.request.UpdateCommentRequest;
import com.example.BloggerApp.http.response.GetCommentResponse;

public interface CommentService {

    GetCommentResponse createComment(CommentRequest commentRequest,Long blogId);

    GetCommentResponse updateComment(UpdateCommentRequest updateCommentRequest,Long blogId);

    void deleteCommentById(Long id);
}
