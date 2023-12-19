package com.example.BloggerApp.service.impl;

import com.example.BloggerApp.http.request.CommentRequest;
import com.example.BloggerApp.http.request.UpdateCommentRequest;
import com.example.BloggerApp.http.response.GetCommentResponse;
import com.example.BloggerApp.models.BlogEntity;
import com.example.BloggerApp.models.Comment;
import com.example.BloggerApp.repository.BlogRepository;
import com.example.BloggerApp.repository.CommentRepository;
import com.example.BloggerApp.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public GetCommentResponse createComment(CommentRequest commentRequest,Long blogId) {
        Comment comment = modelMapper.map(commentRequest,Comment.class);
        BlogEntity blogEntity = blogRepository.findById(blogId).orElseThrow(() -> new RuntimeException("No Blog Found"));
        comment.setBlogEntity(blogEntity);
        Comment savedComment = commentRepository.save(comment);
        return modelMapper.map(savedComment,GetCommentResponse.class);
    }

    @Override
    public GetCommentResponse updateComment(UpdateCommentRequest updateCommentRequest,Long blogId) {
        BlogEntity blogEntity = blogRepository.findById(blogId).orElseThrow(() -> new RuntimeException("No Blog Found"));
        Comment comment = commentRepository.findById(updateCommentRequest.getId()).orElseThrow(() -> new RuntimeException("No comment exist with this id"));
        comment.setContent(updateCommentRequest.getContent());
        comment.setBlogEntity(blogEntity);
        Comment updatedComment = commentRepository.save(comment);
        return modelMapper.map(updatedComment,GetCommentResponse.class);
    }

    @Override
    public void deleteCommentById(Long id) {
     commentRepository.deleteById(id);
    }
}
