package com.example.BloggerApp.http.request;

import lombok.Getter;

import java.util.List;

@Getter
public class CreateBlogRequest {
    private String title;

    private String subtitle;

    private List<TagRequest> tagRequests;

    private String body;

}
