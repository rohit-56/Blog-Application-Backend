package com.example.BloggerApp.common.exceptions;

public class DatabaseException extends RuntimeException{

    private final Integer statusCode;

    public Integer getStatusCode() {
        return statusCode;
    }

    public DatabaseException(String message, Integer statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public DatabaseException(String message, Throwable cause,Integer statusCode) {
        super(message, cause);
        this.statusCode = statusCode;
    }
}
