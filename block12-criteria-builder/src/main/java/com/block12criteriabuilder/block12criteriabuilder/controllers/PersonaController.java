package com.block12criteriabuilder.block12criteriabuilder.controllers;



import com.block12criteriabuilder.block12criteriabuilder.dto.input.PersonaInputDto;
import com.block12criteriabuilder.block12criteriabuilder.entities.PersonaEntity;
import com.block12criteriabuilder.block12criteriabuilder.exception.CustomError;
import com.block12criteriabuilder.block12criteriabuilder.exception.EntityNotFoundException;
import com.block12criteriabuilder.block12criteriabuilder.mapper.PersonaMapper;
import com.block12criteriabuilder.block12criteriabuilder.services.PersonaService;
import com.block12criteriabuilder.block12criteriabuilder.services.ProfesorService;
import com.block12criteriabuilder.block12criteriabuilder.services.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.block12criteriabuilder.block12criteriabuilder.exception.UnprocessableEntityException;

import java.util.Date;
import java.util.List;



@AllArgsConstructor
@RestController
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;
    @Autowired
    private PersonaMapper personaMapper;
    @Autowired
    private StudentService studentService;
    @Autowired
    private ProfesorService profesorService;


    @GetMapping("cb/search")
    public ResponseEntity<List<PersonaEntity>> buscarPersonas(
            @RequestParam(required = false) String user,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String surname,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaCreacionDesde,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaCreacionHasta,
            @RequestParam(required = false) String orderBy) {

        List<PersonaEntity> personasEntity = personaService.buscarPersonas(user, name, surname, fechaCreacionDesde, fechaCreacionHasta, orderBy);
        return new ResponseEntity<>(personasEntity, HttpStatus.OK);
    }


    @CrossOrigin(origins = "https://cdpn.io")
    @PostMapping("/addperson")
    public PersonaEntity addPerson(@RequestBody PersonaEntity person) {
        return personaService.agregarPersona(person);
    }


    @GetMapping("/getall")
    public List<PersonaEntity> getAllPersons() {
        return personaService.mostrarTodos();
    }



    @PostMapping()
    public ResponseEntity<?> agregarPersona(@RequestBody PersonaInputDto personaInputDto) {
        try {
            // Convertir el DTO a una entidad PersonaEntity usando el mapper
            PersonaEntity nuevaPersonaEntity = personaMapper.toEntity(personaInputDto);

            // Guardar la entidad en la base de datos
            PersonaEntity personaGuardada = personaService.agregarPersona(nuevaPersonaEntity);

            // Convertir la entidad guardada a DTO y devolverla en la respuesta
            PersonaInputDto personaGuardadaDTO = personaMapper.toDTO(personaGuardada);

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
    public ResponseEntity<?> modificarPersona(@PathVariable Integer id, @RequestBody PersonaInputDto personaInputDto) {
        try {
            // Convertir el DTO a una entidad PersonaEntity usando el mapper
            PersonaEntity personaEntity = personaMapper.toEntity(personaInputDto);

            // Modificar la entidad en la base de datos
            PersonaEntity personaModificadaEntity = personaService.modificarPersona(id, personaEntity);

            // Convertir la entidad modificada a DTO y devolverla en la respuesta
            PersonaInputDto personaModificadaDTO = personaMapper.toDTO(personaModificadaEntity);

            return new ResponseEntity<>(personaModificadaDTO, HttpStatus.OK);
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
            try {
                // Intentar eliminar un estudiante con el id de la persona
                studentService.deleteStudent(id);
            } catch (EntityNotFoundException e) {
                // Ignorar si no se encuentra el estudiante
            }

            try {
                // Intentar eliminar un profesor con el id de la persona
                profesorService.deleteProfesor(id);
            } catch (EntityNotFoundException e) {
                // Ignorar si no se encuentra el profesor
            }

            // Proceder a eliminar la persona
            personaService.borrarPersona(id);
            return ResponseEntity.ok("Persona con id " + id + " eliminada correctamente");
        } catch (EntityNotFoundException e) {
            CustomError error = new CustomError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), e.getExternalMessage());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        } catch (UnprocessableEntityException e) {
            CustomError error = new CustomError(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getExternalMessage());
            return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }


}
