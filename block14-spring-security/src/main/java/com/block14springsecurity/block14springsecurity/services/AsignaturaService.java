package com.block14springsecurity.block14springsecurity.services;



import com.block14springsecurity.block14springsecurity.dto.input.AsignaturaInputDTO;
import com.block14springsecurity.block14springsecurity.entities.AsignaturaEntity;
import com.block14springsecurity.block14springsecurity.entities.StudentEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AsignaturaService {

    List<AsignaturaInputDTO> getAllAsignaturas();

    List<AsignaturaEntity> getAsignaturasByStudentId(Integer idStudent);

    AsignaturaInputDTO createAsignatura(AsignaturaInputDTO asignaturaInputDTO);

    ResponseEntity<?> deleteAsignatura(Integer idAsignatura);

    List<StudentEntity> getStudentByAsignaturaId(Integer idAsignatura);
}

