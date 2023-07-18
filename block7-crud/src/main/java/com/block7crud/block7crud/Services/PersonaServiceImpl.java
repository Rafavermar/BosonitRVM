package com.block7crud.block7crud.Services;

import java.util.List;

import com.block7crud.block7crud.Entities.Persona;
import com.block7crud.block7crud.Repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaServiceImpl implements PersonaService {
    private final PersonaRepository personaRepository;

    @Autowired
    public PersonaServiceImpl(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Override
    public Persona agregarPersona(Persona persona) {
        return personaRepository.save(persona);
    }

    @Override
    public Persona modificarPersona(Integer id, Persona persona) {
        Persona personaExistente = personaRepository.findById(id).orElse(null);
        if (personaExistente != null) {
            if (persona.getNombre() != null) {
                personaExistente.setNombre(persona.getNombre());
            }
            if (persona.getEdad() != null) {
                personaExistente.setEdad(persona.getEdad());
            }
            if (persona.getPoblacion() != null) {
                personaExistente.setPoblacion(persona.getPoblacion());
            }
            return personaRepository.save(personaExistente);
        } else {
            return null;
        }
    }

    @Override
    public void borrarPersona(Integer id) {
        personaRepository.deleteById(id);
    }

    @Override
    public Persona obtenerPersonaPorId(Integer id) {
        return personaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Persona> obtenerPersonasPorNombre(String nombre) {
        return personaRepository.findByNombre(nombre);
    }

    @Override
    public List<Persona> obtenerTodasLasPersonas() {
        return personaRepository.findAll();
    }
}
