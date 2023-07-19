package com.block7crudvalidation.block7crudvalidation.Exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends ApplicationException {
    private static final String EXTERNAL_MESSAGE = "Todos los campos (nombre, edad, población) deben estar presentes y no pueden estar vacíos.";

    public BadRequestException() {
        super(EXTERNAL_MESSAGE, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
