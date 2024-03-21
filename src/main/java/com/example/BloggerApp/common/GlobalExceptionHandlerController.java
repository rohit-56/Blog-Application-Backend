package com.example.BloggerApp.common;

import com.example.BloggerApp.common.exceptions.DatabaseException;
import com.example.BloggerApp.common.exceptions.UnauthorizedException;
import com.example.BloggerApp.common.exceptions.UserAlreadyExist;
import com.example.BloggerApp.common.http.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


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

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleException(Exception unauthorizedException){
        unauthorizedException.printStackTrace();
        ErrorResponse errorResponse = new ErrorResponse.Builder(unauthorizedException.getMessage(), 500).build();
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ErrorResponse> handleAccessException(AccessDeniedException unauthorizedException){
        ErrorResponse errorResponse = new ErrorResponse.Builder(unauthorizedException.getMessage(), 403).build();
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InsufficientAuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAccessException(InsufficientAuthenticationException unauthorizedException){
        ErrorResponse errorResponse = new ErrorResponse.Builder(unauthorizedException.getMessage(), 402).build();
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponse> handleAccessException(NullPointerException nullPointerException){
        ErrorResponse errorResponse = new ErrorResponse.Builder(nullPointerException.getMessage(), 404).build();
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }


}
