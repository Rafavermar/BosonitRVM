package com.block7crudvalidation.block7crudvalidation.Controllers;

import com.block7crudvalidation.block7crudvalidation.DTO.EstudianteFullDTO;
import com.block7crudvalidation.block7crudvalidation.DTO.EstudianteSimpleDTO;
import com.block7crudvalidation.block7crudvalidation.Entities.PersonaEntity;
import com.block7crudvalidation.block7crudvalidation.Entities.StudentEntity;
import com.block7crudvalidation.block7crudvalidation.Exception.CustomError;
import com.block7crudvalidation.block7crudvalidation.Exception.UnprocessableEntityException;
import com.block7crudvalidation.block7crudvalidation.Services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {

    private final StudentService studentService;

    public EstudianteController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping()
    public ResponseEntity<?> agregarEstudiante(@RequestBody EstudianteSimpleDTO estudianteDTO) {
        try {
            // Retrieve the associated PersonaEntity id from EstudianteDTO
            Integer personaId = estudianteDTO.getPersonaId();

            // Check if the PersonaEntity with the given id exists
            PersonaEntity personaEntity = personaService.getPersonaById(personaId);
            if (personaEntity == null) {
                // Return an error response if the PersonaEntity doesn't exist
                CustomError error = new CustomError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Persona with id " + personaId + " not found");
                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
            }

            // Create a new student entity and set the associated PersonaEntity
            StudentEntity studentEntity = convertToEntity(estudianteDTO);
            studentEntity.setPersona(personaEntity);

            // Save the new student
            StudentEntity newStudent = studentService.saveStudent(studentEntity);
            EstudianteDTO nuevaEstudianteDTO = convertToDTO(newStudent);
            return new ResponseEntity<>(nuevaEstudianteDTO, HttpStatus.CREATED);
        } catch (UnprocessableEntityException e) {
            CustomError error = new CustomError(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getExternalMessage());
            return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id, @RequestParam(defaultValue = "simple") String outputType) {
        try {
            if ("full".equalsIgnoreCase(outputType)) {
                EstudianteFullDTO estudianteFullDTO = studentService.getStudentFullDetails(id);
                if (estudianteFullDTO != null) {
                    return new ResponseEntity<>(estudianteFullDTO, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            } else {
                EstudianteSimpleDTO estudianteSimpleDTO = studentService.getStudentSimpleDetails(id);
                if (estudianteSimpleDTO != null) {
                    return new ResponseEntity<>(estudianteSimpleDTO, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            }
        } catch (UnprocessableEntityException e) {
            CustomError error = new CustomError(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getExternalMessage());
            return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }


    @GetMapping()
    public ResponseEntity<List<EstudianteDTO>> mostrarTodos() {
        // Similar implementation as in PersonaController for getting all students
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modificarEstudiante(@PathVariable Integer id, @RequestBody EstudianteDTO estudianteDTO) {
        // Similar implementation as in PersonaController for updating a student
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarEstudiante(@PathVariable Long id) {
        // Similar implementation as in PersonaController for deleting a student
    }

}
