package com.block12criteriabuilder.block12criteriabuilder.services;


import com.block12criteriabuilder.block12criteriabuilder.dto.input.AsignaturaInputDTO;
import com.block12criteriabuilder.block12criteriabuilder.entities.AsignaturaEntity;
import com.block12criteriabuilder.block12criteriabuilder.entities.StudentEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AsignaturaService {

    List<AsignaturaInputDTO> getAllAsignaturas();

    List<AsignaturaEntity> getAsignaturasByStudentId(Integer idStudent);

    AsignaturaInputDTO createAsignatura(AsignaturaInputDTO asignaturaInputDTO);

    ResponseEntity<?> deleteAsignatura(Integer idAsignatura);

    List<StudentEntity> getStudentByAsignaturaId(Integer idAsignatura);
}

