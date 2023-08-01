package com.jpacascade.jpacascade.dataInitializers;

import com.jpacascade.jpacascade.entities.CabeceraFra;
import com.jpacascade.jpacascade.entities.Cliente;
import com.jpacascade.jpacascade.entities.LineasFra;
import com.jpacascade.jpacascade.repositories.CabeceraFraRepository;
import com.jpacascade.jpacascade.repositories.ClienteRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.Arrays;
@Component
public class DataInitializer {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CabeceraFraRepository cabeceraFraRepository;

    @PostConstruct
    public void initializeData() {
        // Insertar un nuevo cliente
        Cliente cliente = new Cliente();
        cliente.setNombre("Pepito");
        cliente = clienteRepository.save(cliente);

        // Crear una factura de prueba asignada al cliente
        CabeceraFra cabeceraFra = new CabeceraFra();
        cabeceraFra.setCliCodi(cliente);

        LineasFra linea1 = new LineasFra();
        linea1.setProNomb("Producto A");
        linea1.setCantidad(5.0);
        linea1.setPrecio(100.0);
        linea1.setCabeceraFra(cabeceraFra);

        LineasFra linea2 = new LineasFra();
        linea2.setProNomb("Producto B");
        linea2.setCantidad(2.0);
        linea2.setPrecio(50.0);
        linea2.setCabeceraFra(cabeceraFra);

        cabeceraFra.setLineas(Arrays.asList(linea1, linea2));

        // Calcular importe total
        double importeTotal = cabeceraFra.getLineas().stream()
                .mapToDouble(linea -> linea.getCantidad() * linea.getPrecio())
                .sum();
        cabeceraFra.setImporte(importeTotal);

        //  un save para insertar las 2 líneas y la cabecera de fra
        cabeceraFra = cabeceraFraRepository.save(cabeceraFra);
    }
}
