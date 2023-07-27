package com.block10dockerizeapp.block10dockerizeapp.controllers;

import com.block10dockerizeapp.block10dockerizeapp.dto.input.ProfesorInputDto;
import com.block10dockerizeapp.block10dockerizeapp.dto.output.ProfesorFullOutputDto;
import com.block10dockerizeapp.block10dockerizeapp.entities.ProfesorEntity;
import com.block10dockerizeapp.block10dockerizeapp.exception.CustomError;
import com.block10dockerizeapp.block10dockerizeapp.exception.EntityNotFoundException;
import com.block10dockerizeapp.block10dockerizeapp.exception.UnprocessableEntityException;
import com.block10dockerizeapp.block10dockerizeapp.mapper.ProfesorMapper;
import com.block10dockerizeapp.block10dockerizeapp.services.PersonaService;
import com.block10dockerizeapp.block10dockerizeapp.services.ProfesorService;
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
    public ResponseEntity<?> agregarProfesor(@RequestBody ProfesorInputDto profesorInputDto) {
        try {
            ProfesorInputDto nuevoProfesorInputDto = profesorService.createProfesor(profesorInputDto);
            return new ResponseEntity<>(nuevoProfesorInputDto, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            CustomError error = new CustomError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }



    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerProfesorPorId(@PathVariable Integer id,
                                                  @RequestParam(required = false, defaultValue = "simple") String outputType) {
        try {
            if ("full".equalsIgnoreCase(outputType)) {
                // Obtener los datos completos del profesor y la persona asociada
                ProfesorFullOutputDto profesorFullDTO = profesorService.getProfesorFullDetailsById(id);
                return ResponseEntity.ok(profesorFullDTO);
            } else {
                // Obtener solo los datos básicos del profesor
                ProfesorInputDto profesorInputDto = profesorService.getProfesorDTOById(id); // Necesitas implementar este método en el servicio
                return ResponseEntity.ok(profesorInputDto);
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
            List<ProfesorFullOutputDto> profesorFullDTOs = profesorEntities.stream()
                    .map(profesor -> profesorService.getProfesorFullDetailsById(profesor.getIdProfesor()))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(profesorFullDTOs);
        } else {
            // Obtener la versión simple de los profesores
            List<ProfesorInputDto> profesorInputDtos = profesorMapper.toDTOList(profesorEntities);
            return ResponseEntity.ok(profesorInputDtos);
        }
    }
    @GetMapping("/nombre/{name}")
    public ResponseEntity<?> getProfesoresByName(@PathVariable String name,
                                                 @RequestParam(required = false, defaultValue = "simple") String outputType) {
        try {
            List<ProfesorInputDto> profesorInputDtos = profesorService.getProfesoresDTOByName(name);

            if (!profesorInputDtos.isEmpty()) {
                if ("full".equalsIgnoreCase(outputType)) {
                    List<ProfesorFullOutputDto> profesoresFullDTOs = profesorInputDtos.stream()
                            .map(profesorDTO -> profesorService.getProfesorFullDetailsById(profesorDTO.getIdProfesor()))
                            .collect(Collectors.toList());
                    return ResponseEntity.ok(profesoresFullDTOs);
                } else {
                    return ResponseEntity.ok(profesorInputDtos);
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
    public ResponseEntity<?> actualizarProfesor(@PathVariable Integer id, @RequestBody ProfesorInputDto profesorInputDto) {
        try {
            ProfesorInputDto updatedProfesorInputDto = profesorService.updateProfesor(id, profesorInputDto);
            return ResponseEntity.ok(updatedProfesorInputDto);
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
