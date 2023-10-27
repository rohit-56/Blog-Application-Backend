package com.example.BloggerApp.http.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetTagResponse {

    @JsonProperty("id")
    private long id;

    @JsonProperty("tag")
    private String tag;
}
