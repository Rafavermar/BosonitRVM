package com.block7crudvalidation.block7crudvalidation.Controllers;

import com.block7crudvalidation.block7crudvalidation.DTO.Input.AsignaturaInputDTO;
import com.block7crudvalidation.block7crudvalidation.Entities.AsignaturaEntity;
import com.block7crudvalidation.block7crudvalidation.Entities.StudentEntity;
import com.block7crudvalidation.block7crudvalidation.Mapper.AsignaturaMapper;
import com.block7crudvalidation.block7crudvalidation.services.AsignaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/asignaturas")
public class AsignaturaController {

    @Autowired
    private AsignaturaService asignaturaService;

    @Autowired
    private AsignaturaMapper asignaturaMapper;

    @GetMapping("/all")
    public ResponseEntity<List<AsignaturaInputDTO>> obtenerTodasLasAsignaturas() {
        List<AsignaturaInputDTO> asignaturas = asignaturaService.getAllAsignaturas();
        return new ResponseEntity<>(asignaturas, HttpStatus.OK);
    }

    @GetMapping("/student/{idStudent}")
    public ResponseEntity<List<AsignaturaInputDTO>> getAsignaturasByStudentId(@PathVariable Integer idStudent) {
        List<AsignaturaEntity> asignaturas = asignaturaService.getAsignaturasByStudentId(idStudent);
        return ResponseEntity.ok(asignaturaMapper.toDTOList(asignaturas));
    }

    @GetMapping("/{idAsignatura}/students")
    public ResponseEntity<List<StudentEntity>> getStudentsByAsignaturaId(@PathVariable Integer idAsignatura) {
        List<StudentEntity> students = asignaturaService.getStudentByAsignaturaId(idAsignatura);
        return ResponseEntity.ok(students);
    }


    @PostMapping
    public ResponseEntity<AsignaturaInputDTO> createAsignatura(@RequestBody AsignaturaInputDTO asignaturaInputDTO) {
        AsignaturaInputDTO asignaturaDTO = asignaturaService.createAsignatura(asignaturaInputDTO);
        return ResponseEntity.ok(asignaturaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAsignatura(@PathVariable Integer id) {
        return asignaturaService.deleteAsignatura(id);
    }


}

