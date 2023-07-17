package com.block6.simplecontrollers.block6.simplecontrollers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controlador1")
public class Controlador1 {
    private final CiudadService ciudadService;

    public Controlador1(CiudadService ciudadService) {
        this.ciudadService = ciudadService;
    }

    @PostMapping("/addCiudad")
    public ResponseEntity<Ciudad> addCiudad(@RequestBody Ciudad ciudad) {
        ciudadService.agregarCiudad(ciudad);
        return ResponseEntity.ok(ciudad);
    }
}
