package com.block6.simplecontrollers.block6.simplecontrollers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/controlador")
public class Controlador {

    private final Map<String, Persona> personas;

    public Controlador(@Qualifier("bean1") Persona persona1,
                       @Qualifier("bean2") Persona persona2,
                       @Qualifier("bean3") Persona persona3) {
        personas = new HashMap<>();
        personas.put("bean1", persona1);
        personas.put("bean2", persona2);
        personas.put("bean3", persona3);
    }

    @GetMapping("/bean/{bean}")
    public Persona getPersona(@PathVariable("bean") String bean) {
        return personas.get(bean);
    }
}
