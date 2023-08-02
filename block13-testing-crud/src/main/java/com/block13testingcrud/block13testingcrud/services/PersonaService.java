package com.block13testingcrud.block13testingcrud.services;



import com.block13testingcrud.block13testingcrud.entities.PersonaEntity;

import java.util.List;

public interface PersonaService {
    PersonaEntity agregarPersona(PersonaEntity personaEntity);
    PersonaEntity buscarPorId(Integer id);
    PersonaEntity buscarPorUsuario(String usuario);
    List<PersonaEntity> mostrarTodos();
    void borrarPersona(Integer id);
    PersonaEntity modificarPersona(int id, PersonaEntity personaEntity);
}
