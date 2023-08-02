package com.block13testingcrud.block13testingcrud.exception;

import org.springframework.http.HttpStatus;


public class EntityByNameNotFoundException extends ApplicationException {
    private static final String DEFAULT_EXTERNAL_MESSAGE = "Entity not found";
    private static final HttpStatus DEFAULT_STATUS_CODE = HttpStatus.NOT_FOUND;

    public EntityByNameNotFoundException() {
        super(DEFAULT_EXTERNAL_MESSAGE, DEFAULT_STATUS_CODE);
    }

    public EntityByNameNotFoundException(String usuario) {
        super(String.format(DEFAULT_EXTERNAL_MESSAGE + ": %s", usuario), DEFAULT_STATUS_CODE);
    }

    public EntityByNameNotFoundException(String externalMessage, HttpStatus statusCode) {
        super(externalMessage, statusCode);
    }
}
