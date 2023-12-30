package com.example.BloggerApp.http.request;


import lombok.Data;

@Data
public class LoginRequest {

    private  String username;

    private String password;
}
