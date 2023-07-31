package com.block12criteriabuilder.block12criteriabuilder.exception;

import org.springframework.http.HttpStatus;



public class EntityNotFoundException extends ApplicationException {

    private static final String EXTERNAL_MESSAGE = "La entidad con ID: %s no fue encontrada.";

    public EntityNotFoundException(int id) {
        super(String.format(EXTERNAL_MESSAGE, id), HttpStatus.NOT_FOUND);
    }

    public EntityNotFoundException(String usuario) {
        super(String.format(EXTERNAL_MESSAGE, usuario), HttpStatus.NOT_FOUND);
    }
}
