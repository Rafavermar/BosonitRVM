package com.block12criteriabuilder.block12criteriabuilder.services;



import com.block12criteriabuilder.block12criteriabuilder.entities.PersonaEntity;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface PersonaService {
    PersonaEntity agregarPersona(PersonaEntity personaEntity);
    PersonaEntity buscarPorId(Integer id);
    PersonaEntity buscarPorUsuario(String usuario);
    List<PersonaEntity> mostrarTodos();
    void borrarPersona(Integer id);
    PersonaEntity modificarPersona(int id, PersonaEntity personaEntity);
    List<PersonaEntity> buscarPersonas(String user, String name, String surname, Date fechaCreacionDesde, Date fechaCreacionHasta, String orderBy, Pageable pageable);
}
