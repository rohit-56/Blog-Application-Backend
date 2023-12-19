package com.example.BloggerApp.http.response;


import com.example.BloggerApp.models.Comment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetBlogResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("subtitle")
    private String subtitle;

    @JsonProperty("getTagResponses")
    private List<GetTagResponse> getTagResponses;

    @JsonProperty("body")
    private String body;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("image_cover")
    private String imageCover;

    @JsonProperty("category_id")
    private Long categoryId;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("comments")
    public Set<Comment> comments = new HashSet<>();

}
