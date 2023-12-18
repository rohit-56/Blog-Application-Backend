package com.example.BloggerApp.http.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class UpdateCategoryRequest {

    @JsonProperty("id")
    private long id;

    @JsonProperty("title")
    private String title;
}
