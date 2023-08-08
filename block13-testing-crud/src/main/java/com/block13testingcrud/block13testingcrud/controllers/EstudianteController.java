package com.block13testingcrud.block13testingcrud.controllers;



import com.block13testingcrud.block13testingcrud.dto.input.StudentInputDto;
import com.block13testingcrud.block13testingcrud.services.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {

    private final StudentService studentService;

    @Autowired
    public EstudianteController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<?>> obtenerTodosLosEstudiantes(
            @RequestParam(required = false, defaultValue = "simple") String outputType) {
        return new ResponseEntity<>(
                outputType.equals("full")
                        ? studentService.getStudentFullDetails()
                        : studentService.getAllStudentsSimpleDetails(),
                HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<StudentInputDto> agregarEstudiante(@RequestBody StudentInputDto studentInputDto) {
        return new ResponseEntity<>(studentService.agregarEstudiante(studentInputDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerEstudiantePorId(@PathVariable Integer id,
                                                    @RequestParam(required = false, defaultValue = "simple") String outputType) {
        return new ResponseEntity<>(outputType.equals("full") ? studentService.getStudentFullDetails(id) : studentService.getStudentDTOById(id), HttpStatus.OK);
    }

    @GetMapping("/nombre/{name}")
    public ResponseEntity<List<?>> getEstudiantesByName(@PathVariable String name,
                                                        @RequestParam(required = false, defaultValue = "simple") String outputType) {
        return new ResponseEntity<>(outputType.equals("full") ? studentService.getStudentFullDetailsByName(name) : studentService.getStudentsDTOByName(name), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentInputDto> actualizarEstudiante(@PathVariable Integer id, @RequestBody StudentInputDto studentInputDto) {
        return new ResponseEntity<>(studentService.agregarEstudiante(studentInputDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarEstudiante(@PathVariable Integer id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<>("Estudiante eliminado con éxito", HttpStatus.OK);
    }

    @PutMapping("/{idStudent}/asignaturas")
    public ResponseEntity<String> assignAsignaturasToStudent(@PathVariable Integer idStudent, @RequestBody List<Integer> idsAsignaturas) {
        studentService.asignarAsignaturasStudent(idStudent, idsAsignaturas);
        return new ResponseEntity<>("Asignaturas asignadas con éxito al estudiante.", HttpStatus.OK);
    }

    @PutMapping("/{idStudent}/desasignar-asignaturas")
    public ResponseEntity<String> unassignAsignaturasFromStudent(@PathVariable Integer idStudent, @RequestBody List<Integer> idsAsignaturas) {
        studentService.desasignarAsignaturasStudent(idStudent, idsAsignaturas);
        return new ResponseEntity<>("Asignaturas desasignadas con éxito del estudiante.", HttpStatus.OK);
    }
}
