package com.block13testingcrud.block13testingcrud.exception;

import org.springframework.http.HttpStatus;



public class UnprocessableEntityException extends ApplicationException {

    private static final String EXTERNAL_MESSAGE = "Entidad no procesable: %s";

    public UnprocessableEntityException(String message) {
        super(String.format(EXTERNAL_MESSAGE, message), HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
