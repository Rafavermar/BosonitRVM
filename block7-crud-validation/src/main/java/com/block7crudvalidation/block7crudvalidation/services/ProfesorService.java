package com.block7crudvalidation.block7crudvalidation.services;

import com.block7crudvalidation.block7crudvalidation.dto.input.ProfesorInputDto;
import com.block7crudvalidation.block7crudvalidation.dto.output.ProfesorFullOutputDto;
import com.block7crudvalidation.block7crudvalidation.entities.ProfesorEntity;

import java.util.List;

public interface ProfesorService {

    ProfesorEntity saveProfesor(ProfesorEntity profesor);

    List<ProfesorEntity> getAllProfesores();

    ProfesorEntity getProfesorById(Integer id);

    void deleteProfesor(Integer id);

    ProfesorEntity updateProfesor(Integer id, ProfesorEntity profesor);

    // New methods
    ProfesorInputDto getProfesorDTOById(Integer id);

    List<ProfesorInputDto> getProfesoresDTOByName(String name);

    ProfesorFullOutputDto getProfesorFullDetailsById(Integer id);

    List<ProfesorFullOutputDto> getProfesorFullDetailsByName(String name);
}
