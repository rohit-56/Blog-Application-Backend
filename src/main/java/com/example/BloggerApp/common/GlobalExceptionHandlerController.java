package com.example.BloggerApp.common;

import com.example.BloggerApp.common.exceptions.DatabaseException;
import com.example.BloggerApp.common.exceptions.UnauthorizedException;
import com.example.BloggerApp.common.exceptions.UserAlreadyExist;
import com.example.BloggerApp.common.http.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandlerController {

    @ExceptionHandler(UserAlreadyExist.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyException(UserAlreadyExist userAlreadyExist){
        ErrorResponse errorResponse = new ErrorResponse.Builder(userAlreadyExist.getMessage(), userAlreadyExist.getStatusCode()).build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<ErrorResponse> handleDatabaseException(DatabaseException databaseException){
        ErrorResponse errorResponse = new ErrorResponse.Builder(databaseException.getMessage(), databaseException.getStatusCode()).build();
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedException(UnauthorizedException unauthorizedException){
        ErrorResponse errorResponse = new ErrorResponse.Builder(unauthorizedException.getMessage(), unauthorizedException.getStatusCode()).build();
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_GATEWAY);
    }

}
