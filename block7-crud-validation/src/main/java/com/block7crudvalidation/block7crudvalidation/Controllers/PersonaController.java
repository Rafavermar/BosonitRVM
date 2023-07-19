package com.block7crudvalidation.block7crudvalidation.Controllers;

import com.block7crudvalidation.block7crudvalidation.DTO.PersonaDTO;
import com.block7crudvalidation.block7crudvalidation.Exception.BadRequestException;
import com.block7crudvalidation.block7crudvalidation.Exception.CustomError;
import com.block7crudvalidation.block7crudvalidation.Exception.UnprocessableEntityException;
import com.block7crudvalidation.block7crudvalidation.Services.PersonaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @PostMapping()
    public ResponseEntity<?> agregarPersona(@RequestBody PersonaDTO personaDTO) {
        try {
            PersonaDTO nuevaPersonaDTO = personaService.agregarPersona(personaDTO);
            return new ResponseEntity<>(nuevaPersonaDTO, HttpStatus.CREATED);
        } catch (UnprocessableEntityException e) {
            CustomError error = new CustomError(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getExternalMessage());
            return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonaDTO> buscarPorId(@PathVariable int id) {
        PersonaDTO personaDTO = personaService.buscarPorId(id);
        return new ResponseEntity<>(personaDTO, HttpStatus.OK);
    }

    @GetMapping("/usuario/{usuario}")
    public ResponseEntity<PersonaDTO> buscarPorUsuario(@PathVariable String usuario) {
        PersonaDTO personaDTO = personaService.buscarPorUsuario(usuario);
        return new ResponseEntity<>(personaDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PersonaDTO>> mostrarTodos() {
        List<PersonaDTO> personasDTO = personaService.mostrarTodos();
        return new ResponseEntity<>(personasDTO, HttpStatus.OK);
    }
}

