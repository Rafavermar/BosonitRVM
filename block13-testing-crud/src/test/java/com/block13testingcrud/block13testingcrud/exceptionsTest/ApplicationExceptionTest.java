package com.block13testingcrud.block13testingcrud.exceptionsTest;
import com.block13testingcrud.block13testingcrud.exception.ApplicationException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationExceptionTest {

    @Test
    public void testExceptionAttributes() {
        String externalMessage = "Test message";
        HttpStatus statusCode = HttpStatus.BAD_REQUEST;

        ApplicationException exception = new ApplicationException(externalMessage, statusCode);

        assertEquals(externalMessage, exception.getExternalMessage());
        assertEquals(statusCode, exception.getStatusCode());
    }

    @Test
    public void testExceptionThrowing() {
        String externalMessage = "Test message";
        HttpStatus statusCode = HttpStatus.BAD_REQUEST;

        ApplicationException thrownException = assertThrows(
                ApplicationException.class,
                () -> {
                    throw new ApplicationException(externalMessage, statusCode);
                }
        );

        assertEquals(externalMessage, thrownException.getExternalMessage());
        assertEquals(statusCode, thrownException.getStatusCode());
    }
}

