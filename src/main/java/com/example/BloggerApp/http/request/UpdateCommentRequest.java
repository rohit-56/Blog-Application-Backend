package com.example.BloggerApp.http.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UpdateCommentRequest {

    @JsonProperty("id")
    private long id;

    @JsonProperty("content")
    private String content;
}
