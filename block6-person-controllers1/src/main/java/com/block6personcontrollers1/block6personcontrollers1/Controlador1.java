package com.block6personcontrollers1.block6personcontrollers1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controlador1")
public class Controlador1 {
    private final PersonaService personaService;

    public Controlador1(PersonaService personaService) {
        this.personaService = personaService;
    }

    @GetMapping("/addPersona")
    public Persona addPersona(@RequestHeader("nombre") String nombre,
                              @RequestHeader("poblacion") String poblacion,
                              @RequestHeader("edad") int edad) {
        return personaService.agregarPersona(nombre, poblacion, edad);
    }
}
