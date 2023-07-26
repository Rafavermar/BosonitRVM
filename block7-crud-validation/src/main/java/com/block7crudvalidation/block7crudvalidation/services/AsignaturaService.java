package com.block7crudvalidation.block7crudvalidation.services;

import com.block7crudvalidation.block7crudvalidation.DTO.Input.AsignaturaInputDTO;
import com.block7crudvalidation.block7crudvalidation.Entities.AsignaturaEntity;
import com.block7crudvalidation.block7crudvalidation.Entities.StudentEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AsignaturaService {

    List<AsignaturaInputDTO> getAllAsignaturas();

    List<AsignaturaEntity> getAsignaturasByStudentId(Integer idStudent);

    AsignaturaInputDTO createAsignatura(AsignaturaInputDTO asignaturaInputDTO);

    ResponseEntity<?> deleteAsignatura(Integer idAsignatura);

    List<StudentEntity> getStudentByAsignaturaId(Integer idAsignatura);
}

