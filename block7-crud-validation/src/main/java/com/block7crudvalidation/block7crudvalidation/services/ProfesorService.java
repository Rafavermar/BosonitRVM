package com.block7crudvalidation.block7crudvalidation.services;

import com.block7crudvalidation.block7crudvalidation.dto.input.ProfesorDTO;
import com.block7crudvalidation.block7crudvalidation.dto.output.ProfesorFullDTO;
import com.block7crudvalidation.block7crudvalidation.entities.ProfesorEntity;

import java.util.List;

public interface ProfesorService {

    ProfesorEntity saveProfesor(ProfesorEntity profesor);

    List<ProfesorEntity> getAllProfesores();

    ProfesorEntity getProfesorById(Integer id);

    void deleteProfesor(Integer id);

    ProfesorEntity updateProfesor(Integer id, ProfesorEntity profesor);

    // New methods
    ProfesorDTO getProfesorDTOById(Integer id);

    List<ProfesorDTO> getProfesoresDTOByName(String name);

    ProfesorFullDTO getProfesorFullDetailsById(Integer id);

    List<ProfesorFullDTO> getProfesorFullDetailsByName(String name);
}
