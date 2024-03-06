package com.example.BloggerApp.common.http.response;

public class ErrorResponse {
    private final String message;

    private final Integer statusCode;

    public ErrorResponse(Builder builder){
      this.message = builder.message;
      this.statusCode = builder.statusCode;
    }

    public String getMessage() {
        return message;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public static final class Builder{
        private String message;
        private Integer statusCode;

        public Builder(String message,Integer statusCode){
            this.message = message;
            this.statusCode = statusCode;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Integer getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(Integer statusCode) {
            this.statusCode = statusCode;
        }

        public ErrorResponse build(){
            return new ErrorResponse(this);
        }
    }
}

