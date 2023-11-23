package com.example.BloggerApp.http.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetCategoryResponse {

    @JsonProperty("id")
    private long id;

    @JsonProperty("title")
    private String title;
}
