package com.jpacascade.jpacascade.dtos.input;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FacturaInputDto {
    int cliCodi; // ID del cliente
    List<LineaInputDto> lineas;

}
