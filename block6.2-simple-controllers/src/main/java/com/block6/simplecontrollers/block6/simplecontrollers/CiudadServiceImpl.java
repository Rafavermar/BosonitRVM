package com.block6.simplecontrollers.block6.simplecontrollers;

import com.block6.simplecontrollers.block6.simplecontrollers.Ciudad;
import com.block6.simplecontrollers.block6.simplecontrollers.CiudadService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CiudadServiceImpl implements CiudadService {
    private List<Ciudad> ciudades = new ArrayList<>();

    @Override
    public void agregarCiudad(Ciudad ciudad) {
        ciudades.add(ciudad);
    }

    @Override
    public List<Ciudad> obtenerCiudades() {
        return ciudades;
    }
}
