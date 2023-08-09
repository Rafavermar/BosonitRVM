package com.block14springsecurity.block14springsecurity.exception;

import org.springframework.http.HttpStatus;



public class PersonaNotFoundException extends ApplicationException {

    private static final String EXTERNAL_MESSAGE = "La persona con id: %s no existe.";

    public PersonaNotFoundException(int idPersona) {
        super(String.format(EXTERNAL_MESSAGE, idPersona), HttpStatus.NOT_FOUND);

    }

}
