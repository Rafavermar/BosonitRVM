package com.block7crudvalidation.block7crudvalidation.services;

import com.block7crudvalidation.block7crudvalidation.dto.input.AsignaturaInputDTO;
import com.block7crudvalidation.block7crudvalidation.entities.AsignaturaEntity;
import com.block7crudvalidation.block7crudvalidation.entities.StudentEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AsignaturaService {

    List<AsignaturaInputDTO> getAllAsignaturas();

    List<AsignaturaEntity> getAsignaturasByStudentId(Integer idStudent);

    AsignaturaInputDTO createAsignatura(AsignaturaInputDTO asignaturaInputDTO);

    ResponseEntity<?> deleteAsignatura(Integer idAsignatura);

    List<StudentEntity> getStudentByAsignaturaId(Integer idAsignatura);
}

