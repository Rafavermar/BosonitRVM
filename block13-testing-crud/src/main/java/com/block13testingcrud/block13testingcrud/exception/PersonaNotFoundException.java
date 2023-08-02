package com.block13testingcrud.block13testingcrud.exception;

import org.springframework.http.HttpStatus;



public class PersonaNotFoundException extends ApplicationException {

    private static final String EXTERNAL_MESSAGE = "La persona con id: %s no existe.";

    public PersonaNotFoundException(int idPersona) {
        super(String.format(EXTERNAL_MESSAGE, idPersona), HttpStatus.NOT_FOUND);

    }
    public PersonaNotFoundException(String usuario) {
        super(String.format(EXTERNAL_MESSAGE, usuario), HttpStatus.NOT_FOUND);
    }
}
