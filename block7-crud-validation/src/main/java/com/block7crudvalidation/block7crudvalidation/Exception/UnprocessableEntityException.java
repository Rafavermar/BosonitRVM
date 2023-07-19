package com.block7crudvalidation.block7crudvalidation.Exception;

import org.springframework.http.HttpStatus;

public class UnprocessableEntityException extends ApplicationException {

    private static final String EXTERNAL_MESSAGE = "Entidad no procesable: %s";

    public UnprocessableEntityException(String message) {
        super(String.format(EXTERNAL_MESSAGE, message), HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
