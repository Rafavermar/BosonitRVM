package com.block7crudvalidation.block7crudvalidation.Services;

import com.block7crudvalidation.block7crudvalidation.DTO.Input.ProfesorDTO;
import com.block7crudvalidation.block7crudvalidation.DTO.Output.ProfesorFullDTO;
import com.block7crudvalidation.block7crudvalidation.Entities.ProfesorEntity;

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
