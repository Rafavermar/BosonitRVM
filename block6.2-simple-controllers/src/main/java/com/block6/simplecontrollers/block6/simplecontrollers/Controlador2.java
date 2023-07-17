package com.block6.simplecontrollers.block6.simplecontrollers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/controlador2")
public class Controlador2 {
    private final CiudadService ciudadService;

    public Controlador2(CiudadService ciudadService) {
        this.ciudadService = ciudadService;
    }

    @GetMapping("/getCiudades")
    public List<Ciudad> getCiudades() {
        return ciudadService.obtenerCiudades();
    }
}
