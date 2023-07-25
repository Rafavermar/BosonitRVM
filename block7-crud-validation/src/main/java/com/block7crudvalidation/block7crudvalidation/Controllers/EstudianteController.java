package com.block7crudvalidation.block7crudvalidation.Controllers;


import com.block7crudvalidation.block7crudvalidation.DTO.Input.StudentDTO;
import com.block7crudvalidation.block7crudvalidation.DTO.Output.EstudianteFullDTO;
import com.block7crudvalidation.block7crudvalidation.Entities.PersonaEntity;
import com.block7crudvalidation.block7crudvalidation.Entities.ProfesorEntity;
import com.block7crudvalidation.block7crudvalidation.Entities.StudentEntity;
import com.block7crudvalidation.block7crudvalidation.Exception.CustomError;
import com.block7crudvalidation.block7crudvalidation.Exception.EntityNotFoundException;
import com.block7crudvalidation.block7crudvalidation.Exception.UnprocessableEntityException;
import com.block7crudvalidation.block7crudvalidation.Mapper.StudentMapper;
import com.block7crudvalidation.block7crudvalidation.Respository.ProfesorRepository;
import com.block7crudvalidation.block7crudvalidation.services.PersonaService;
import com.block7crudvalidation.block7crudvalidation.services.ProfesorService;
import com.block7crudvalidation.block7crudvalidation.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {

    private final StudentService studentService;
    private final StudentMapper studentMapper;
    private final PersonaService personaService;
    private final ProfesorRepository profesorRepository;
    private final ProfesorService profesorService;

    @Autowired
    public EstudianteController(StudentService studentService, StudentMapper studentMapper,
                                PersonaService personaService, ProfesorRepository profesorRepository,
                                ProfesorService profesorService) {
        this.studentService = studentService;
        this.studentMapper = studentMapper;
        this.personaService = personaService;
        this.profesorRepository = profesorRepository;
        this.profesorService = profesorService;
    }

    @GetMapping
    public ResponseEntity<?> obtenerTodosLosEstudiantes(@RequestParam(required = false, defaultValue = "simple") String outputType) {
        try {
            // Obtener la lista de todas las entidades estudiante desde el servicio
            List<StudentEntity> studentEntities = studentService.getAllStudents();

            if ("full".equalsIgnoreCase(outputType)) {
                // Convertir la lista de entidades a lista de FullDTOs usando el servicio
                List<EstudianteFullDTO> estudianteFullDTOs = studentEntities.stream()
                        .map(studentEntity -> studentService.getStudentFullDetails(studentEntity.getIdStudent()))
                        .collect(Collectors.toList());

                return new ResponseEntity<>(estudianteFullDTOs, HttpStatus.OK);
            } else {
                // Convertir la lista de entidades a lista de DTOs simples usando el mapper
                List<StudentDTO> studentDTOs = studentEntities.stream()
                        .map(studentMapper::toDTO)
                        .collect(Collectors.toList());

                return new ResponseEntity<>(studentDTOs, HttpStatus.OK);
            }
        } catch (Exception e) {
            CustomError error = new CustomError(System.currentTimeMillis(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint para agregar un estudiante
    @PostMapping
    public ResponseEntity<?> agregarEstudiante(@RequestBody StudentDTO studentDTO) {
        try {
            // Guardar el estudiante en la base de datos
            StudentDTO nuevoEstudianteDTO = studentService.agregarEstudiante(studentDTO);
            return new ResponseEntity<>(nuevoEstudianteDTO, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            CustomError error = new CustomError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), e.getExternalMessage());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        } catch (UnprocessableEntityException e) {
            CustomError error = new CustomError(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getExternalMessage());
            return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    // Endpoint para obtener un estudiante por su ID y el tipo de salida (simple o full)
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerEstudiantePorId(@PathVariable Integer id,
                                                    @RequestParam(required = false, defaultValue = "simple") String outputType) {
        try {
            if ("full".equalsIgnoreCase(outputType)) {
                // Obtener los datos completos del estudiante y la persona asociada
                EstudianteFullDTO estudianteFullDTO = studentService.getStudentFullDetails(id);
                return ResponseEntity.ok(estudianteFullDTO);
            } else {
                // Obtener solo los datos básicos del estudiante
                StudentDTO studentDTO = studentService.getStudentDTOById(id); // Using the new method
                return ResponseEntity.ok(studentDTO);
            }
        } catch (EntityNotFoundException e) {
            CustomError error = new CustomError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/nombre/{name}")
    public ResponseEntity<?> getEstudiantesByName(@PathVariable String name,
                                                  @RequestParam(required = false, defaultValue = "simple") String outputType) {
        try {
            List<StudentDTO> studentDTOs = studentService.getStudentsDTOByName(name);

            if (!studentDTOs.isEmpty()) {
                if ("full".equalsIgnoreCase(outputType)) {
                    List<EstudianteFullDTO> estudiantesFullDTOs = studentDTOs.stream()
                            .map(studentDTO -> studentService.getStudentFullDetails(studentDTO.getIdStudent()))
                            .collect(Collectors.toList());
                    return ResponseEntity.ok(estudiantesFullDTOs);
                } else {
                    return ResponseEntity.ok(studentDTOs);
                }
            } else {
                CustomError error = new CustomError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Students with name " + name + " not found");
                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
            }
        } catch (EntityNotFoundException e) {
            CustomError error = new CustomError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), e.getExternalMessage());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarEstudiante(@PathVariable Integer id, @RequestBody StudentDTO studentDTO) {
        try {
            // Buscar la entidad PersonaEntity por su ID
            PersonaEntity personaEntity = personaService.buscarPorId(studentDTO.getIdPersona());

            // Obtener el estudiante por su ID desde el servicio
            StudentEntity studentEntity = studentService.getStudentById(id);

            // Buscar la entidad ProfesorEntity por su ID
            ProfesorEntity profesorEntity = null;
            if (studentDTO.getIdProfesor() != null) {
                profesorEntity = profesorRepository.findById(studentDTO.getIdProfesor())
                        .orElseThrow(() -> new EntityNotFoundException("Profesor with id " + studentDTO.getIdProfesor() + " not found"));
            }

            // Establecer la entidad PersonaEntity en la entidad StudentEntity actualizada
            studentEntity.setPersona(personaEntity);
            studentEntity.setNumHoursWeek(studentDTO.getNumHoursWeek());
            studentEntity.setComments(studentDTO.getComments());
            studentEntity.setBranch(studentDTO.getBranch());

            // Establecer la entidad ProfesorEntity en la entidad StudentEntity actualizada
            studentEntity.setProfesor(profesorEntity);

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
            return ResponseEntity.ok("Estudiante eliminado con éxito");
        } catch (EntityNotFoundException e) {
            CustomError error = new CustomError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), e.getExternalMessage());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para asignar asignaturas a un estudiante
    @PutMapping("/{idStudent}/asignaturas")
    public ResponseEntity<?> assignAsignaturasToStudent(@PathVariable Integer idStudent, @RequestBody List<Integer> idsAsignaturas) {
        try {
            studentService.asignarAsignaturasStudent(idStudent, idsAsignaturas);
            return ResponseEntity.ok("Asignaturas asignadas con éxito al estudiante.");
        } catch (EntityNotFoundException e) {
            // Aquí puedes manejar el error como mejor te parezca
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para desasignar asignaturas de un estudiante:
    @PutMapping("/{idStudent}/desasignar-asignaturas")
    public ResponseEntity<?> unassignAsignaturasFromStudent(@PathVariable Integer idStudent, @RequestBody List<Integer> idsAsignaturas) {
        try {
            studentService.desasignarAsignaturasStudent(idStudent, idsAsignaturas);
            return ResponseEntity.ok("Asignaturas desasignadas con éxito del estudiante.");
        } catch (EntityNotFoundException e) {
            // Aquí puedes manejar el error como mejor te parezca
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
