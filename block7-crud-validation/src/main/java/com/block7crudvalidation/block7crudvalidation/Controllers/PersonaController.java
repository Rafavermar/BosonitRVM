package com.block7crudvalidation.block7crudvalidation.Controllers;

import com.block7crudvalidation.block7crudvalidation.DTO.Input.PersonaDTO;
import com.block7crudvalidation.block7crudvalidation.Entities.PersonaEntity;
import com.block7crudvalidation.block7crudvalidation.Exception.CustomError;
import com.block7crudvalidation.block7crudvalidation.Exception.EntityNotFoundException;
import com.block7crudvalidation.block7crudvalidation.Exception.UnprocessableEntityException;
import com.block7crudvalidation.block7crudvalidation.Mapper.PersonaMapper;
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
    @Autowired
    private PersonaMapper personaMapper;

    @PostMapping()
    public ResponseEntity<?> agregarPersona(@RequestBody PersonaDTO personaDTO) {
        try {
            // Convertir el DTO a una entidad PersonaEntity usando el mapper
            PersonaEntity nuevaPersonaEntity = personaMapper.toEntity(personaDTO);

            // Guardar la entidad en la base de datos
            PersonaEntity personaGuardada = personaService.agregarPersona(nuevaPersonaEntity);

            // Convertir la entidad guardada a DTO y devolverla en la respuesta
            PersonaDTO personaGuardadaDTO = personaMapper.toDTO(personaGuardada);

            return new ResponseEntity<>(personaGuardadaDTO, HttpStatus.CREATED);
        } catch (UnprocessableEntityException e) {
            CustomError error = new CustomError(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getExternalMessage());
            return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<PersonaEntity> buscarPorId(@PathVariable Integer id) {
        PersonaEntity personaEntity = personaService.buscarPorId(id);
        return new ResponseEntity<>(personaEntity, HttpStatus.OK);
    }

    @GetMapping("/usuario/{usuario}")
    public ResponseEntity<PersonaEntity> buscarPorUsuario(@PathVariable String usuario) {
        PersonaEntity personaEntity = personaService.buscarPorUsuario(usuario);
        return new ResponseEntity<>(personaEntity, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PersonaEntity>> mostrarTodos() {
        List<PersonaEntity> personasEntity = personaService.mostrarTodos();
        return new ResponseEntity<>(personasEntity, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modificarPersona(@PathVariable Integer id, @RequestBody PersonaEntity personaEntity) {
        try {
            PersonaEntity personaModificadaEntity = personaService.modificarPersona(id, personaEntity);
            return new ResponseEntity<>(personaModificadaEntity, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            CustomError error = new CustomError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), e.getExternalMessage());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        } catch (UnprocessableEntityException e) {
            CustomError error = new CustomError(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getExternalMessage());
            return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarPersona(@PathVariable Integer id) {
        try {
            personaService.borrarPersona(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            CustomError error = new CustomError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), e.getExternalMessage());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        } catch (UnprocessableEntityException e) {
            CustomError error = new CustomError(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getExternalMessage());
            return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
