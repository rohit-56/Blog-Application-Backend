package com.example.BloggerApp.http.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class CreateCategoryRequest {

    @JsonProperty("title")
    private String title;
}
