package com.jpacascade.jpacascade.Controllers;

import com.jpacascade.jpacascade.dtos.input.LineaInputDto;
import com.jpacascade.jpacascade.dtos.output.FacturaOutputDto;

import com.jpacascade.jpacascade.exceptions.NotFoundException;
import com.jpacascade.jpacascade.services.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/factura")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @GetMapping
    public ResponseEntity<List<FacturaOutputDto>> getAllFacturas() {
        List<FacturaOutputDto> facturas = facturaService.getAllFacturas();
        return new ResponseEntity<>(facturas, HttpStatus.OK);
    }

    @DeleteMapping("/{idFra}")
    public ResponseEntity<?> deleteFactura(@PathVariable Integer idFra) {
        try {
            facturaService.deleteFactura(idFra);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/linea/{idFra}")
    public ResponseEntity<FacturaOutputDto> addLinea(@PathVariable Integer idFra, @RequestBody LineaInputDto linea) {
        try {
            FacturaOutputDto factura = facturaService.addLinea(idFra, linea);
            return new ResponseEntity<>(factura, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public ResponseEntity<FacturaOutputDto> createFactura(@RequestBody FacturaInputDto facturaInputDto) {
        FacturaOutputDto factura = facturaService.createFactura(facturaInputDto);
        return new ResponseEntity<>(factura, HttpStatus.CREATED);
    }

}
