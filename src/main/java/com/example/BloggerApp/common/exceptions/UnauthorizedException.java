package com.example.BloggerApp.common.exceptions;

public class UnauthorizedException  extends RuntimeException{

    private final Integer statusCode;

    public Integer getStatusCode() {
        return statusCode;
    }

    public UnauthorizedException(String message, Integer statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public UnauthorizedException(String message, Throwable cause,Integer statusCode) {
        super(message, cause);
        this.statusCode = statusCode;
    }
}
