package com.block14springsecurity.block14springsecurity.services;


import com.block14springsecurity.block14springsecurity.dto.input.ProfesorInputDto;
import com.block14springsecurity.block14springsecurity.dto.output.ProfesorFullOutputDto;
import com.block14springsecurity.block14springsecurity.entities.ProfesorEntity;

import java.util.List;

public interface ProfesorService {

    ProfesorEntity saveProfesor(ProfesorEntity profesor);

    List<ProfesorEntity> getAllProfesores();

    ProfesorEntity getProfesorById(Integer id);

    void deleteProfesor(Integer id);

    ProfesorEntity updateProfesor(Integer id, ProfesorEntity profesor);

    ProfesorEntity updateProfesorEntity(Integer id, ProfesorEntity profesorEntity);

    ProfesorInputDto updateProfesor(Integer id, ProfesorInputDto profesorInputDto);

    // New methods
    ProfesorInputDto getProfesorDTOById(Integer id);

    List<ProfesorInputDto> getProfesoresDTOByName(String name);

    ProfesorFullOutputDto getProfesorFullDetailsById(Integer id);

    List<ProfesorFullOutputDto> getProfesorFullDetailsByName(String name);

    ProfesorInputDto createProfesor(ProfesorInputDto profesorInputDto);

}
