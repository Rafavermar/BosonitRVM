package com.jpacascade.jpacascade.dtos.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LineaInputDto {
    private String producto;
    private double cantidad;
    private double importe;
    private int idFra;

    // Getters and setters
}

