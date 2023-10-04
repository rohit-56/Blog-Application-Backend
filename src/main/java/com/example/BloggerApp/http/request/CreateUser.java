package com.example.BloggerApp.http.request;

import lombok.Getter;
import lombok.Setter;

@Getter
public class CreateUser {

    public String username;

    public String email;

    public String password;
}
