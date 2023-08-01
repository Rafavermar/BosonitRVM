package com.jpacascade.jpacascade.dtos.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FacturaOutputDto {
    private int id;
    private double importe;
    private ClienteOutputDto clienteOutputDto;
    private List<LineaOutputDto> lineaOutputDtoList;
}