package com.block6.simplecontrollers.block6.simplecontrollers;

import com.block6.simplecontrollers.block6.simplecontrollers.Ciudad;

import java.util.List;

public interface CiudadService {
    void agregarCiudad(Ciudad ciudad);
    List<Ciudad> obtenerCiudades();
}
