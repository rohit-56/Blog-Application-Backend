package com.example.BloggerApp.http.request;

import com.example.BloggerApp.models.TagEntity;
import lombok.Getter;

import java.util.List;

@Getter
public class UpdateBlogRequest {

    private long id;

    private String title;

    private String subtitle;

    private List<TagRequest> tagRequests;

    private String body;

    private String imageCover;
}
