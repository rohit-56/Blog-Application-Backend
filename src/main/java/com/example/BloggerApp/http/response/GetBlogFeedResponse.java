package com.example.BloggerApp.http.response;

import com.example.BloggerApp.models.Comment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetBlogFeedResponse {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("subtitle")
    private String subtitle;

    @JsonProperty("getTagResponses")
    private List<GetTagResponse> getTagResponses;

    @JsonProperty("content")
    private String content;

    @JsonProperty("createdAt")
    private String createdAt;

    @JsonProperty("cover")
    private byte[] imageCover;

    @JsonProperty("category")
    private String category;

    @JsonProperty("authorName")
    private String authorName;

    @JsonProperty("authorAvatar")
    private String authorAvatar;

    @JsonProperty("comments")
    public Set<Comment> comments = new HashSet<>();

}
