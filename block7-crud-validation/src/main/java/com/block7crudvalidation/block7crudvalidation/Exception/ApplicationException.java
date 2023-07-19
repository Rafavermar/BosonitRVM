package com.block7crudvalidation.block7crudvalidation.Exception;

import org.springframework.http.HttpStatus;

public class ApplicationException extends RuntimeException {
    private final String externalMessage;
    private final HttpStatus statusCode;

    public ApplicationException(String externalMessage, HttpStatus statusCode) {
        this.externalMessage = externalMessage;
        this.statusCode = statusCode;
    }

    public ApplicationException(String externalMessage, HttpStatus statusCode, String[] args) {
        this.externalMessage = String.format(externalMessage, args);
        this.statusCode = statusCode;
    }

    public String getExternalMessage() {
        return externalMessage;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }
}
