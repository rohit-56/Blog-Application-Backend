package com.example.BloggerApp.common.exceptions;

public class UserAlreadyExist extends RuntimeException{

private final Integer statusCode;
    public UserAlreadyExist(String message,Integer statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public UserAlreadyExist(String message, Throwable cause,Integer statusCode) {
        super(message, cause);
       this.statusCode = statusCode;
    }

    public Integer getStatusCode() {
        return statusCode;
    }
}
