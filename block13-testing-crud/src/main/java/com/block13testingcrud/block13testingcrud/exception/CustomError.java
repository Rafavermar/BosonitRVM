package com.block13testingcrud.block13testingcrud.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomError {
    private long timestamp;
    private int httpCode;
    private String mensaje;
}



