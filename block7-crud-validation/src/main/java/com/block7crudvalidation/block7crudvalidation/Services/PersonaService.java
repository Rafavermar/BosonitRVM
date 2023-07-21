package com.block7crudvalidation.block7crudvalidation.Services;

import com.block7crudvalidation.block7crudvalidation.DTO.Input.PersonaDTO;

import java.util.List;

public interface PersonaService {

    PersonaDTO agregarPersona(PersonaDTO personaDTO);

    PersonaDTO buscarPorId(Integer id);

    PersonaDTO buscarPorUsuario(String usuario);

    List<PersonaDTO> mostrarTodos();

    void borrarPersona(int id);

    PersonaDTO modificarPersona(int id, PersonaDTO personaDTO);
}
