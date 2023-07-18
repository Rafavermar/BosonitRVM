package com.block7crud.block7crud.Services;
import com.block7crud.block7crud.Entities.Persona;

import java.util.List;

public interface PersonaService {
    Persona agregarPersona(Persona persona);
    Persona modificarPersona(Integer id, Persona persona);
    void borrarPersona(Integer id);
    Persona obtenerPersonaPorId(Integer id);
    List<Persona> obtenerPersonasPorNombre(String nombre);
    List<Persona> obtenerTodasLasPersonas();
}
