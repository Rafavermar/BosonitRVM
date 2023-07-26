package com.block7crudvalidation.block7crudvalidation.exception;

import org.springframework.http.HttpStatus;

public class EntityByNameNotFoundException extends ApplicationException {
    private static final String EXTERNAL_MESSAGE = "La entidad con nombre: %s no fue encontrada.";


    public EntityByNameNotFoundException(String usuario) {
        super(String.format(EXTERNAL_MESSAGE, usuario), HttpStatus.NOT_FOUND);
    }

    public EntityByNameNotFoundException(String externalMessage, HttpStatus statusCode) {
        super(externalMessage, statusCode);
    }
}

