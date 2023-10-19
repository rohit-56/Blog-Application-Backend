package com.example.BloggerApp.http.request;

import lombok.Getter;

@Getter
public class UpdateUser {

    public Long id;

    public String username;

    public String email;

    public String password;

    public String image;

    public String bio;
}
