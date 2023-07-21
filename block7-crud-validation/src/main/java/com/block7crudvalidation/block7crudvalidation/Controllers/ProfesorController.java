package com.block7crudvalidation.block7crudvalidation.Controllers;

import com.block7crudvalidation.block7crudvalidation.DTO.Input.ProfesorDTO;
import com.block7crudvalidation.block7crudvalidation.Entities.PersonaEntity;
import com.block7crudvalidation.block7crudvalidation.Entities.ProfesorEntity;
import com.block7crudvalidation.block7crudvalidation.Exception.CustomError;
import com.block7crudvalidation.block7crudvalidation.Exception.EntityNotFoundException;
import com.block7crudvalidation.block7crudvalidation.Exception.UnprocessableEntityException;
import com.block7crudvalidation.block7crudvalidation.Mapper.ProfesorMapper;
import com.block7crudvalidation.block7crudvalidation.Services.PersonaService;
import com.block7crudvalidation.block7crudvalidation.Services.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profesores")
public class ProfesorController {

    private final ProfesorService profesorService;
    private final ProfesorMapper profesorMapper;
    private final PersonaService personaService;

    @Autowired
    public ProfesorController(ProfesorService profesorService, ProfesorMapper profesorMapper, PersonaService personaService) {
        this.profesorService = profesorService;
        this.profesorMapper = profesorMapper;
        this.personaService = personaService;
    }

    @PostMapping("/profesores")
    public ResponseEntity<?> agregarProfesor(@RequestBody ProfesorDTO profesorDTO) {
        try {
            // Buscar la entidad PersonaEntity por su ID
            PersonaEntity personaEntity = personaService.buscarPorId(profesorDTO.getPersona().getIdPersona());

            // Convertir el DTO a una entidad ProfesorEntity usando el mapper
            ProfesorEntity profesorEntity = profesorMapper.toEntity(profesorDTO);

            // Establecer la entidad PersonaEntity en la nueva entidad ProfesorEntity
            profesorEntity.setPersona(personaEntity);

            // Guardar el profesor en la base de datos
            ProfesorEntity nuevoProfesor = profesorService.saveProfesor(profesorEntity);

            // Convertir el profesor guardado a DTO y devolverlo en la respuesta
            ProfesorDTO nuevoProfesorDTO = profesorMapper.toDTO(nuevoProfesor);
            return new ResponseEntity<>(nuevoProfesorDTO, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            CustomError error = new CustomError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), e.getExternalMessage());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        } catch (UnprocessableEntityException e) {
            CustomError error = new CustomError(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getExternalMessage());
            return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProfesorDTO> obtenerProfesorPorId(@PathVariable Integer id) {
        ProfesorEntity profesorEntity = profesorService.getProfesorById(id);
        if (profesorEntity == null) {
            return ResponseEntity.notFound().build();
        }
        ProfesorDTO profesorDTO = profesorMapper.toDTO(profesorEntity);
        return ResponseEntity.ok(profesorDTO);
    }

    @GetMapping
    public ResponseEntity<List<ProfesorDTO>> obtenerTodosLosProfesores() {
        List<ProfesorEntity> profesorEntities = profesorService.getAllProfesores();
        List<ProfesorDTO> profesorDTOs = profesorMapper.toDTOList(profesorEntities);
        return ResponseEntity.ok(profesorDTOs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfesorDTO> actualizarProfesor(@PathVariable Integer id, @RequestBody ProfesorDTO profesorDTO) {
        ProfesorEntity profesorEntity = profesorMapper.toEntity(profesorDTO);
        ProfesorEntity updatedProfesor = profesorService.updateProfesor(id, profesorEntity);
        ProfesorDTO updatedProfesorDTO = profesorMapper.toDTO(updatedProfesor);
        return ResponseEntity.ok(updatedProfesorDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProfesor(@PathVariable Integer id) {
        profesorService.deleteProfesor(id);
        return ResponseEntity.noContent().build();
    }
}
