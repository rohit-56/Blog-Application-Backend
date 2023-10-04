package com.example.BloggerApp.http.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetUserResponse {

    public Long id;

    public String username;

    public String email;

    public String password;
}
