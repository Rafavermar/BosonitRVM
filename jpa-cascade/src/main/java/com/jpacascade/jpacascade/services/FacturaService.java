package com.jpacascade.jpacascade.services;

import com.jpacascade.jpacascade.dtos.input.LineaInputDto;
import com.jpacascade.jpacascade.dtos.output.FacturaOutputDto;
import com.jpacascade.jpacascade.exceptions.NotFoundException;


import java.util.List;

public interface FacturaService {
    List<FacturaOutputDto> getAllFacturas();
    void deleteFactura(Integer idFra) throws NotFoundException;
    FacturaOutputDto addLinea(Integer idFra, LineaInputDto linea) throws NotFoundException;
}
