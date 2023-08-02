package com.block13testingcrud.block13testingcrud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<CustomError> handleApplicationException(ApplicationException ex) {
        CustomError error = new CustomError();
        error.setTimestamp(System.currentTimeMillis());
        error.setHttpCode(ex.getStatusCode().value());
        error.setMensaje(ex.getExternalMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(error);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<CustomError> handleEntityNotFoundException(EntityNotFoundException ex) {
        CustomError error = new CustomError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), ex.getExternalMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
