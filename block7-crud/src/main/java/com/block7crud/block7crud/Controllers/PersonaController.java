package com.block7crud.block7crud.Controllers;

import com.block7crud.block7crud.Entities.Persona;
import com.block7crud.block7crud.Services.PersonaService;
import com.block7crud.block7crud.Web.Response.BadRequestException;
import com.block7crud.block7crud.Web.Response.PersonaNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persona")
public class PersonaController {
    private final PersonaService personaService;

    @Autowired
    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @PostMapping
    public ResponseEntity<Persona> agregarPersona(@RequestBody Persona persona) {
        if (persona.getNombre() == null || persona.getNombre().isEmpty() ||
                persona.getEdad() == null || persona.getEdad().isEmpty() ||
                persona.getPoblacion() == null || persona.getPoblacion().isEmpty()) {
            throw new BadRequestException("Todos los campos (nombre, edad, población) deben estar presentes y no pueden estar vacíos.");
        }

        Persona nuevaPersona = personaService.agregarPersona(persona);
        return new ResponseEntity<>(nuevaPersona, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Persona> modificarPersona(@PathVariable Integer id, @RequestBody Persona persona) {
        Persona personaExistente = personaService.obtenerPersonaPorId(id);
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

            Persona personaActualizada = personaService.modificarPersona(id, personaExistente);
            return new ResponseEntity<>(personaActualizada, HttpStatus.OK);
        } else {
            throw new PersonaNotFoundException("Persona no encontrada");
        }
    }




    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarPersona(@PathVariable Integer id) {
        Persona personaExistente = personaService.obtenerPersonaPorId(id);
        if (personaExistente != null) {
            personaService.borrarPersona(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new PersonaNotFoundException("Persona no encontrada");
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Persona> obtenerPersonaPorId(@PathVariable Integer id) {
        Persona persona = personaService.obtenerPersonaPorId(id);
        if (persona != null) {
            return new ResponseEntity<>(persona, HttpStatus.OK);
        } else {
            throw new PersonaNotFoundException("Persona no encontrada");
        }
    }


    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<Persona>> obtenerPersonasPorNombre(@PathVariable String nombre) {
        List<Persona> personas = personaService.obtenerPersonasPorNombre(nombre);
        return new ResponseEntity<>(personas, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Persona>> obtenerTodasLasPersonas() {
        List<Persona> personas = personaService.obtenerTodasLasPersonas();
        return new ResponseEntity<>(personas, HttpStatus.OK);
    }
}
