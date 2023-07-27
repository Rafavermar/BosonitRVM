package com.block10dockerizeapp.block10dockerizeapp.services;


import com.block10dockerizeapp.block10dockerizeapp.dto.input.AsignaturaInputDTO;
import com.block10dockerizeapp.block10dockerizeapp.entities.AsignaturaEntity;
import com.block10dockerizeapp.block10dockerizeapp.entities.StudentEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AsignaturaService {

    List<AsignaturaInputDTO> getAllAsignaturas();

    List<AsignaturaEntity> getAsignaturasByStudentId(Integer idStudent);

    AsignaturaInputDTO createAsignatura(AsignaturaInputDTO asignaturaInputDTO);

    ResponseEntity<?> deleteAsignatura(Integer idAsignatura);

    List<StudentEntity> getStudentByAsignaturaId(Integer idAsignatura);
}

