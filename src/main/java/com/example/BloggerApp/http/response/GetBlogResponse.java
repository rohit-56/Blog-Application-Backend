package com.example.BloggerApp.http.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

}
