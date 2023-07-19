package com.block7crudvalidation.block7crudvalidation.Exception;

import org.springframework.http.HttpStatus;

public class PersonaNotFoundException extends ApplicationException {
    private static final String EXTERNAL_MESSAGE = "La persona con id: %s no existe.";

    public PersonaNotFoundException(int idPersona) {
        super(EXTERNAL_MESSAGE, HttpStatus.NOT_FOUND, new String[]{String.valueOf(idPersona)});
    }
}
