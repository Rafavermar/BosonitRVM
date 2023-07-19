package com.block7crudvalidation.block7crudvalidation.Exception;

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
}
