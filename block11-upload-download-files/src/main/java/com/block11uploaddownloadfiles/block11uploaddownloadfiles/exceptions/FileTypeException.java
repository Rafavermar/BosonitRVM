package com.block11uploaddownloadfiles.block11uploaddownloadfiles.exceptions;

public class FileTypeException extends RuntimeException {
    public FileTypeException(String message) {
        super(message);
    }

    public FileTypeException(String message, Throwable cause) {
        super(message, cause);
    }
}
