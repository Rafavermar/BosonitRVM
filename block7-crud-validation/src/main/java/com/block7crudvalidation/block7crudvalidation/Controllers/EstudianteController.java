package com.block7crudvalidation.block7crudvalidation.Controllers;

import com.block7crudvalidation.block7crudvalidation.DTO.Input.PersonaDTO;
import com.block7crudvalidation.block7crudvalidation.DTO.Input.StudentDTO;
import com.block7crudvalidation.block7crudvalidation.Entities.PersonaEntity;
import com.block7crudvalidation.block7crudvalidation.Entities.StudentEntity;
import com.block7crudvalidation.block7crudvalidation.Exception.CustomError;
import com.block7crudvalidation.block7crudvalidation.Exception.EntityNotFoundException;
import com.block7crudvalidation.block7crudvalidation.Exception.UnprocessableEntityException;
import com.block7crudvalidation.block7crudvalidation.Mapper.PersonaMapper;
import com.block7crudvalidation.block7crudvalidation.Mapper.StudentMapper;
import com.block7crudvalidation.block7crudvalidation.Services.PersonaService;
import com.block7crudvalidation.block7crudvalidation.Services.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {

    private final StudentService studentService;
    private final PersonaService personaService;
    private final StudentMapper studentMapper;
    private final PersonaMapper personaMapper;

    // Endpoint para agregar un estudiante
    @PostMapping
    public ResponseEntity<?> agregarEstudiante(@RequestBody StudentDTO studentDTO) {
        try {
            // Buscar la entidad PersonaEntity por su ID
            PersonaDTO personaDTO = personaService.buscarPorId(studentDTO.getPersonaDTO().getId());

            // Convertir el PersonaDTO a PersonaEntity usando el mapper
            PersonaEntity personaEntity = personaMapper.toEntity(personaDTO);

            // Convertir el DTO a una entidad StudentEntity usando el mapper
            StudentEntity studentEntity = studentMapper.toEntity(studentDTO);
            studentEntity.setPersona(personaEntity);

            // Guardar el estudiante en la base de datos
            StudentEntity nuevoEstudiante = studentService.saveStudent(studentEntity);

            // Convertir el estudiante guardado a DTO y devolverlo en la respuesta
            StudentDTO nuevoEstudianteDTO = studentMapper.toDTO(nuevoEstudiante);
            return new ResponseEntity<>(nuevoEstudianteDTO, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            CustomError error = new CustomError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), e.getExternalMessage());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        } catch (UnprocessableEntityException e) {
            CustomError error = new CustomError(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getExternalMessage());
            return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    // Endpoint para obtener un estudiante por su ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerEstudiantePorId(@PathVariable Integer id) {
        try {
            // Obtener el estudiante por su ID desde el servicio
            StudentEntity studentEntity = studentService.getStudentById(id);

            // Convertir la entidad a DTO usando el mapper
            StudentDTO studentDTO = studentMapper.toDTO(studentEntity);
            return new ResponseEntity<>(studentDTO, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            CustomError error = new CustomError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), e.getExternalMessage());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }


    // Endpoint para actualizar un estudiante por su ID
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarEstudiante(@PathVariable Integer id, @RequestBody StudentDTO studentDTO) {
        try {
            // Buscar la entidad PersonaEntity por su ID
            PersonaDTO personaDTO = personaService.buscarPorId(studentDTO.getPersonaDTO().getId());

            // Obtener el estudiante por su ID desde el servicio
            StudentEntity studentEntity = studentService.getStudentById(id);

            // Convertir el PersonaDTO a PersonaEntity usando el mapper
            PersonaEntity personaEntity = personaMapper.toEntity(personaDTO);

            // Actualizar los campos con los datos recibidos en el DTO
            studentEntity.setPersona(personaEntity);
            studentEntity.setNumHoursWeek(studentDTO.getNumHoursWeek());
            studentEntity.setComments(studentDTO.getComments());
            studentEntity.setBranch(studentDTO.getBranch());

            // Guardar el estudiante actualizado en la base de datos
            StudentEntity estudianteActualizado = studentService.saveStudent(studentEntity);

            // Convertir el estudiante actualizado a DTO y devolverlo en la respuesta
            StudentDTO estudianteActualizadoDTO = studentMapper.toDTO(estudianteActualizado);
            return new ResponseEntity<>(estudianteActualizadoDTO, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            CustomError error = new CustomError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), e.getExternalMessage());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        } catch (UnprocessableEntityException e) {
            CustomError error = new CustomError(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getExternalMessage());
            return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }


    // Endpoint para eliminar un estudiante por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarEstudiante(@PathVariable Integer id) {
        try {
            // Eliminar el estudiante por su ID desde el servicio
            studentService.deleteStudent(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            CustomError error = new CustomError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), e.getExternalMessage());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }

}
