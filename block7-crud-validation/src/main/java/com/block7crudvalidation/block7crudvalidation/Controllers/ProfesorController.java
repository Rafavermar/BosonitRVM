package com.block7crudvalidation.block7crudvalidation.Controllers;

import com.block7crudvalidation.block7crudvalidation.DTO.Input.ProfesorDTO;
import com.block7crudvalidation.block7crudvalidation.DTO.Output.ProfesorFullDTO;
import com.block7crudvalidation.block7crudvalidation.Entities.PersonaEntity;
import com.block7crudvalidation.block7crudvalidation.Entities.ProfesorEntity;
import com.block7crudvalidation.block7crudvalidation.Exception.CustomError;
import com.block7crudvalidation.block7crudvalidation.Exception.EntityNotFoundException;
import com.block7crudvalidation.block7crudvalidation.Exception.UnprocessableEntityException;
import com.block7crudvalidation.block7crudvalidation.Mapper.ProfesorMapper;
import com.block7crudvalidation.block7crudvalidation.services.PersonaService;
import com.block7crudvalidation.block7crudvalidation.services.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @PostMapping()
    public ResponseEntity<?> agregarProfesor(@RequestBody ProfesorDTO profesorDTO) {
        try {
            // Obtener el ID de persona desde el DTO del profesor
            Integer idPersona = profesorDTO.getIdPersona();

            // Buscar la entidad PersonaEntity por su ID desde el servicio
            PersonaEntity personaEntity = personaService.buscarPorId(idPersona);

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
    public ResponseEntity<?> obtenerProfesorPorId(@PathVariable Integer id,
                                                  @RequestParam(required = false, defaultValue = "simple") String outputType) {
        try {
            if ("full".equalsIgnoreCase(outputType)) {
                // Obtener los datos completos del profesor y la persona asociada
                ProfesorFullDTO profesorFullDTO = profesorService.getProfesorFullDetailsById(id);
                return ResponseEntity.ok(profesorFullDTO);
            } else {
                // Obtener solo los datos básicos del profesor
                ProfesorDTO profesorDTO = profesorService.getProfesorDTOById(id); // Necesitas implementar este método en el servicio
                return ResponseEntity.ok(profesorDTO);
            }
        } catch (EntityNotFoundException e) {
            CustomError error = new CustomError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<?> obtenerTodosLosProfesores(@RequestParam(required = false, defaultValue = "simple") String outputType) {
        List<ProfesorEntity> profesorEntities = profesorService.getAllProfesores();

        if ("full".equalsIgnoreCase(outputType)) {
            // Obtener la versión completa de los profesores
            List<ProfesorFullDTO> profesorFullDTOs = profesorEntities.stream()
                    .map(profesor -> profesorService.getProfesorFullDetailsById(profesor.getIdProfesor()))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(profesorFullDTOs);
        } else {
            // Obtener la versión simple de los profesores
            List<ProfesorDTO> profesorDTOs = profesorMapper.toDTOList(profesorEntities);
            return ResponseEntity.ok(profesorDTOs);
        }
    }
    @GetMapping("/nombre/{name}")
    public ResponseEntity<?> getProfesoresByName(@PathVariable String name,
                                                 @RequestParam(required = false, defaultValue = "simple") String outputType) {
        try {
            List<ProfesorDTO> profesorDTOs = profesorService.getProfesoresDTOByName(name);

            if (!profesorDTOs.isEmpty()) {
                if ("full".equalsIgnoreCase(outputType)) {
                    List<ProfesorFullDTO> profesoresFullDTOs = profesorDTOs.stream()
                            .map(profesorDTO -> profesorService.getProfesorFullDetailsById(profesorDTO.getIdProfesor()))
                            .collect(Collectors.toList());
                    return ResponseEntity.ok(profesoresFullDTOs);
                } else {
                    return ResponseEntity.ok(profesorDTOs);
                }
            } else {
                CustomError error = new CustomError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Professors with name " + name + " not found");
                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
            }
        } catch (EntityNotFoundException e) {
            CustomError error = new CustomError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), e.getExternalMessage());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarProfesor(@PathVariable Integer id, @RequestBody ProfesorDTO profesorDTO) {
        try {
            // Obtener el ID de persona desde el DTO del profesor
            Integer idPersona = profesorDTO.getIdPersona();

            // Buscar la entidad PersonaEntity por su ID desde el servicio
            PersonaEntity personaEntity = personaService.buscarPorId(idPersona);

            // Convertir el DTO a una entidad ProfesorEntity usando el mapper
            ProfesorEntity profesorEntity = profesorMapper.toEntity(profesorDTO);

            // Establecer la entidad PersonaEntity en la nueva entidad ProfesorEntity
            profesorEntity.setPersona(personaEntity);

            // Actualizar el profesor en la base de datos
            ProfesorEntity updatedProfesor = profesorService.updateProfesor(id, profesorEntity);

            // Convertir el profesor actualizado a DTO y devolverlo en la respuesta
            ProfesorDTO updatedProfesorDTO = profesorMapper.toDTO(updatedProfesor);
            return ResponseEntity.ok(updatedProfesorDTO);
        } catch (EntityNotFoundException e) {
            CustomError error = new CustomError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), e.getExternalMessage());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        } catch (UnprocessableEntityException e) {
            CustomError error = new CustomError(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getExternalMessage());
            return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProfesor(@PathVariable Integer id) {
        profesorService.deleteProfesor(id);
        return ResponseEntity.ok("Profesor eliminado con éxito");
    }

}
