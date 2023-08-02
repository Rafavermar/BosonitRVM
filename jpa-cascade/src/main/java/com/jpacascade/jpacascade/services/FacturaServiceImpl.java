package com.jpacascade.jpacascade.services;

import com.jpacascade.jpacascade.entities.CabeceraFra;
import com.jpacascade.jpacascade.entities.Cliente;
import com.jpacascade.jpacascade.entities.LineasFra;
import com.jpacascade.jpacascade.dtos.input.FacturaInputDto;
import com.jpacascade.jpacascade.dtos.input.LineaInputDto;
import com.jpacascade.jpacascade.dtos.output.FacturaOutputDto;
import com.jpacascade.jpacascade.exceptions.NotFoundException;
import com.jpacascade.jpacascade.mappers.FacturaMapper;
import com.jpacascade.jpacascade.repositories.CabeceraFraRepository;
import com.jpacascade.jpacascade.repositories.ClienteRepository;
import com.jpacascade.jpacascade.repositories.LineasFraRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacturaServiceImpl implements FacturaService {

    @Autowired
    private CabeceraFraRepository cabeceraFraRepository;

    @Autowired
    private LineasFraRepository lineasFraRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private FacturaMapper facturaMapper;

    @Override
    public List<FacturaOutputDto> getAllFacturas() {
        return cabeceraFraRepository.findAll().stream()
                .map(facturaMapper::toFacturaDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteFactura(Integer idFra) throws NotFoundException {
        CabeceraFra factura = cabeceraFraRepository.findById(idFra)
                .orElseThrow(() -> new NotFoundException("Factura no encontrada"));

        cabeceraFraRepository.delete(factura);
    }

    @Override
    public FacturaOutputDto addLinea(Integer idFra, LineaInputDto linea) throws NotFoundException {
        CabeceraFra factura = cabeceraFraRepository.findById(idFra)
                .orElseThrow(() -> new NotFoundException("Factura no encontrada"));

        LineasFra nuevaLinea = new LineasFra();
        nuevaLinea.setProNomb(linea.getProducto());
        nuevaLinea.setCantidad(linea.getCantidad());
        nuevaLinea.setPrecio(linea.getImporte());
        nuevaLinea.setCabeceraFra(factura);

        factura.getLineas().add(nuevaLinea);
        cabeceraFraRepository.save(factura);

        return facturaMapper.toFacturaDto(factura);
    }

    @Override
    public FacturaOutputDto createFactura(FacturaInputDto facturaInputDto) {
        Cliente cliente = clienteRepository.findById(facturaInputDto.getCliCodi())
                .orElseThrow(() -> new NotFoundException("Cliente no encontrado"));

        CabeceraFra cabeceraFra = new CabeceraFra();
        cabeceraFra.setCliCodi(cliente);

        List<LineasFra> lineas = new ArrayList<>(); // Lista para guardar las líneas de la factura
        double importeTotal = 0.0;

        for (LineaInputDto lineaInputDto : facturaInputDto.getLineas()) {
            LineasFra lineasFra = new LineasFra();
            lineasFra.setProNomb(lineaInputDto.getProducto());
            lineasFra.setCantidad(lineaInputDto.getCantidad());
            lineasFra.setPrecio(lineaInputDto.getImporte());
            lineasFra.setCabeceraFra(cabeceraFra); // Establece la referencia a la factura

            importeTotal += lineaInputDto.getImporte(); // Calcula el importe
            lineas.add(lineasFra); // Añade la línea a la lista
        }

        cabeceraFra.setLineas(lineas); // Asigna la lista de líneas a la cabecera
        cabeceraFra.setImporte(importeTotal); // Establece el importe total en la cabecera
        cabeceraFra = cabeceraFraRepository.save(cabeceraFra);

        lineas.forEach(lineasFraRepository::save); // Guarda las líneas en la base de datos

        return facturaMapper.toFacturaDto(cabeceraFra);
    }


}