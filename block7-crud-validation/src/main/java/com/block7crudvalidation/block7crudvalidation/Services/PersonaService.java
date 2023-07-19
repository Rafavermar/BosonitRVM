package com.block7crudvalidation.block7crudvalidation.Services;

import com.block7crudvalidation.block7crudvalidation.DTO.PersonaDTO;

import java.util.List;

public interface PersonaService {

    public PersonaDTO agregarPersona(PersonaDTO personaDTO);

    PersonaDTO buscarPorId(int id);

    PersonaDTO buscarPorUsuario(String usuario);

    List<PersonaDTO> mostrarTodos();
}

