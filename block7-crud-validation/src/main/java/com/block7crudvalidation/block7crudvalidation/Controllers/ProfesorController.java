package com.block7crudvalidation.block7crudvalidation.Controllers;

import com.block7crudvalidation.block7crudvalidation.DTO.Input.ProfesorDTO;
import com.block7crudvalidation.block7crudvalidation.Entities.ProfesorEntity;
import com.block7crudvalidation.block7crudvalidation.Mapper.ProfesorMapper;
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

    @Autowired
    public ProfesorController(ProfesorService profesorService, ProfesorMapper profesorMapper) {
        this.profesorService = profesorService;
        this.profesorMapper = profesorMapper;
    }

    @PostMapping
    public ResponseEntity<ProfesorDTO> agregarProfesor(@RequestBody ProfesorDTO profesorDTO) {
        ProfesorEntity profesorEntity = profesorMapper.toEntity(profesorDTO);
        ProfesorEntity nuevoProfesor = profesorService.saveProfesor(profesorEntity);
        ProfesorDTO nuevoProfesorDTO = profesorMapper.toDTO(nuevoProfesor);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProfesorDTO);
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
