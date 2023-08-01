package com.jpacascade.jpacascade.dtos.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LineaOutputDto {
    private int id;
    private String producto;
    private double cantidad;
    private double importe;

}