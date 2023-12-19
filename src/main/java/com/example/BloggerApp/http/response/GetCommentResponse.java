package com.example.BloggerApp.http.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Setter
public class GetCommentResponse {

    @JsonProperty("id")
    private long id;

    @JsonProperty("content")
    private String content;
}
