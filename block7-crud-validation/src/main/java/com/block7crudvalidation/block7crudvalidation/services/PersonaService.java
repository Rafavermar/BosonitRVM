package com.block7crudvalidation.block7crudvalidation.services;

import com.block7crudvalidation.block7crudvalidation.entities.PersonaEntity;

import java.util.List;

public interface PersonaService {
    PersonaEntity agregarPersona(PersonaEntity personaEntity);
    PersonaEntity buscarPorId(Integer id);
    PersonaEntity buscarPorUsuario(String usuario);
    List<PersonaEntity> mostrarTodos();
    void borrarPersona(Integer id);
    PersonaEntity modificarPersona(int id, PersonaEntity personaEntity);
}
