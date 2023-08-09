package com.block14springsecurity.block14springsecurity.controllers;

import com.block14springsecurity.block14springsecurity.dto.input.AsignaturaInputDTO;
import com.block14springsecurity.block14springsecurity.entities.AsignaturaEntity;
import com.block14springsecurity.block14springsecurity.entities.StudentEntity;
import com.block14springsecurity.block14springsecurity.exception.EntityNotFoundException;
import com.block14springsecurity.block14springsecurity.mapper.AsignaturaMapper;
import com.block14springsecurity.block14springsecurity.services.AsignaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


import java.util.Collections;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
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

    // TODO sustituir el try catch por un Globalexception handler
    @GetMapping("/student/{idStudent}")
    public ResponseEntity<?> getAsignaturasByStudentId(@PathVariable Integer idStudent) {
        try {
            List<AsignaturaEntity> asignaturas = asignaturaService.getAsignaturasByStudentId(idStudent);
            return ResponseEntity.ok(asignaturaMapper.toDTOList(asignaturas));
        } catch (EntityNotFoundException e) {
            // Devolvemos un mensaje de error personalizado con un código 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("mensaje", e.getMessage()));
        }
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

