package com.block6personcontrollers1.block6personcontrollers1;

import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImpl implements PersonaService {
    private Persona persona;

    @Override
    public Persona agregarPersona(String nombre, String poblacion, int edad) {
        persona = new Persona();
        persona.setNombre(nombre);
        persona.setPoblacion(poblacion);
        persona.setEdad(edad);
        return persona;
    }

    @Override
    public Persona obtenerPersona() {
        return persona;
    }
}
