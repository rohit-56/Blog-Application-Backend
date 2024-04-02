package com.example.BloggerApp.http.response;

import com.example.BloggerApp.models.Roles;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class LoginResponse {

    @JsonProperty("token")
    private String token;

    @JsonProperty("userId")
    public Long userId;

    @JsonProperty("username")
    public String username;

    @JsonProperty("email")
    public String email;

    @JsonProperty("image")
    public String image;

    @JsonProperty("about")
    public String about;

    @JsonProperty("createdDate")
    public Date createdDate;

    @JsonProperty("roles")
    public Set<Roles> roles = new HashSet<>();
}
